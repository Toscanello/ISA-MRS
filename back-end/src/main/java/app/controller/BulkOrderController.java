package app.controller;

import app.domain.Medicine;
import app.domain.MedicineQuantity;
import app.domain.Pharmacy;
import app.domain.bulk_order.BulkOrder;
import app.domain.bulk_order.BulkOrderItem;
import app.domain.bulk_order.OrderResponse;
import app.dto.bulk_order.BulkOrderDTO;
import app.dto.bulk_order.BulkOrderItemDTO;
import app.dto.bulk_order.NamedOrderDTO;
import app.dto.bulk_order.NamedOrderItemDTO;
import app.service.MedicineService;
import app.service.PharmacyService;
import app.service.bulk_order.BulkOrderService;
import app.service.bulk_order.OrderResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping("/api/order")
public class BulkOrderController {
    @Autowired
    private BulkOrderService bulkOrderService;
    @Autowired
    private OrderResponseService orderResponseService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private PharmacyService pharmacyService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> order(@RequestBody BulkOrderDTO orderDTO) {
        BulkOrder order = new BulkOrder();
        order.setDueDate(orderDTO.getDueDateAsDate());
        order.setPharmacy(pharmacyService.getPharmacy(orderDTO.getPharmacyRegNo()));

        for (BulkOrderItemDTO orderItemDTO : orderDTO.getBulkOrderItemList()) {
            BulkOrderItem boi = new BulkOrderItem();
            Medicine orderedMedicine = medicineService.findMedicineByCode(orderItemDTO.getMedicineCode());
            boi.setMedicine(orderedMedicine);
            boi.setAmount(orderItemDTO.getAmount());
            boi.setBulkOrder(order);
            order.addItem(boi);
        }
        bulkOrderService.save(order);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/all/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<NamedOrderDTO>> allOrders(@PathVariable String regNo) {
        List<BulkOrder> pharmacyOrders = bulkOrderService.getAllByPharmacyRegNo(regNo);
        Set<NamedOrderDTO> pharmacyOrdersDTO = new HashSet<>();
        for (BulkOrder bo : pharmacyOrders) {
            pharmacyOrdersDTO.add(new NamedOrderDTO(bo));
        }
        return new ResponseEntity<>(pharmacyOrdersDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/offer/accept/{orderId}/{id}")
    public ResponseEntity<String> acceptOffer(@PathVariable Long orderId, @PathVariable Long id) {
        List<OrderResponse> responses = orderResponseService.getAll();
        for (OrderResponse or : responses) {
            if (!or.getOrder().getId().equals(orderId))
                continue;
            if (or.getId().equals(id))
                accept(or);
            else
                decline(or);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    //TODO: OVDE IMPLEMENTIRATI KONKURENTNO IZVRSAVANJE
    //MOZE SE DESITI DA DVA PHARMACY ADMIN POTVRDE ISTI ORDER
    private void accept(OrderResponse or) {
        or.setStatus(OrderResponse.Status.ACCEPTED);
        //SEND EMAIL WOULD BE IMPLEMENTED IF THERE WAS
        //A SUPPLIER USER ROLE
        BulkOrder bo = or.getOrder();
        Pharmacy p = bo.getPharmacy();
        for (BulkOrderItem boi : bo.getOrderItems()) {
            Medicine m = boi.getMedicine();
            boolean containsMedicine = false;
            for (MedicineQuantity mq : p.getMedicineQuantities()) {
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
                p.getMedicineQuantities().add(mq);
            }
        }
        orderResponseService.save(or);
        pharmacyService.save(p);
    }

    //TODO: IMPLEMENTIRATI KONKURENTNO IZVRSAVANJE, POVEZATI SA PRETHODNOM FUNKCIJOM
    private void decline(OrderResponse or) {
        or.setStatus(OrderResponse.Status.DECLINED);
        orderResponseService.save(or);
    }
}
