package app.service.bulk_order;

import app.domain.Medicine;
import app.domain.MedicinePricing;
import app.domain.MedicineQuantity;
import app.domain.Pharmacy;
import app.domain.bulk_order.BulkOrder;
import app.domain.bulk_order.BulkOrderItem;
import app.domain.bulk_order.OrderResponse;
import app.repository.bulk_order.OrderResponseRepository;
import app.service.MedicinePricingService;
import app.service.MedicineQuantityService;
import app.service.PharmacistService;
import app.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderResponseService {
    @Autowired
    OrderResponseRepository orderResponseRepository;

    MedicineQuantityService medicineQuantityService;
    PharmacyService pharmacyService;
    MedicinePricingService medicinePricingService;

    public void setMedicineQuantityService(MedicineQuantityService mqs) { medicineQuantityService = mqs; }
    public void setPharmacyService(PharmacyService ps) { pharmacyService = ps; }
    public void setMedicinePricingService(MedicinePricingService mps) { medicinePricingService = mps; }

    public List<OrderResponse> getAll() {
        return orderResponseRepository.findAll();
    }

    public List<OrderResponse> getAllByOrderId(Long id) {
        return orderResponseRepository.getAllByOrderId(id);
    }

    public OrderResponse save(OrderResponse or) {
        return orderResponseRepository.save(or);
    }

    public void delete(OrderResponse or) { orderResponseRepository.delete(or); }

    @Transactional
    public void acceptOfferById(Long orderId, Long offerId) {
        List<OrderResponse> responses = orderResponseRepository.getAllByOrderId(orderId);
        for (OrderResponse or : responses) {
            if (or.getId().equals(offerId))
                accept(or);
            else
                decline(or);
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void accept(OrderResponse or) {
        or.setStatus(OrderResponse.Status.ACCEPTED);
        //SEND EMAIL WOULD BE IMPLEMENTED IF THERE WAS
        //A SUPPLIER USER ROLE
        BulkOrder bo = or.getOrder();
        Pharmacy p = bo.getPharmacy();
        List<MedicineQuantity> pharmacyMedicine =
                medicineQuantityService.findMedicineQuantitiesByPharmacyRegNo(p.getRegNo());
        for (BulkOrderItem boi : bo.getOrderItems()) {
            Medicine m = boi.getMedicine();
            boolean containsMedicine = false;
            for (MedicineQuantity mq : pharmacyMedicine) {
                if (mq.getMedicine().getCode().equals(m.getCode())) {
                    mq.setQuantity(mq.getQuantity() + boi.getAmount());
                    containsMedicine = true;
                    break;
                }
            }
            if (!containsMedicine) {
                MedicineQuantity mq = new MedicineQuantity();
                mq.setQuantity(boi.getAmount());
                mq.setMedicine(boi.getMedicine());
                mq.setPharmacy(p);

                MedicinePricing mp = new MedicinePricing();
                mp.setPricingStart(LocalDateTime.now());
                mp.setPricingEnd(null);
                mp.setMedicine(boi.getMedicine());
                mp.setPharmacy(bo.getPharmacy());
                mp.setPrice(0.);

                medicinePricingService.save(mp);
                pharmacyMedicine.add(mq);
            }
        }
        p.setMedicineQuantities(new HashSet<>(pharmacyMedicine));
        orderResponseRepository.save(or);
        pharmacyService.save(p);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void decline(OrderResponse or) {
        or.setStatus(OrderResponse.Status.DECLINED);
        orderResponseRepository.save(or);
    }
}
