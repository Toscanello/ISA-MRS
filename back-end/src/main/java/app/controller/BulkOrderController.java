package app.controller;

import app.domain.Medicine;
import app.domain.MedicineQuantity;
import app.domain.Pharmacy;
import app.domain.bulk_order.BulkOrder;
import app.domain.bulk_order.BulkOrderItem;
import app.domain.bulk_order.OrderResponse;
import app.dto.bulk_order.*;
import app.repository.MedicineQuantityRepository;
import app.service.MedicinePricingService;
import app.service.MedicineQuantityService;
import app.service.MedicineService;
import app.service.PharmacyService;
import app.service.bulk_order.BulkOrderService;
import app.service.bulk_order.OrderResponseService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    private MedicineQuantityService medicineQuantityService;
    @Autowired
    private MedicinePricingService medicinePricingService;
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

    @GetMapping(value = "/offers/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<OrderResponseDTO>> allOffers(@PathVariable Long orderId) {
        List<OrderResponse> allResponses = orderResponseService.getAllByOrderId(orderId);
        Set<OrderResponseDTO> allResponsesDTO = new HashSet<>();
        for (OrderResponse or : allResponses) {
            allResponsesDTO.add(new OrderResponseDTO(or));
        }
        return new ResponseEntity<>(allResponsesDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/offer/accept/{orderId}/{id}")
    public ResponseEntity<String> acceptOffer(@PathVariable Long orderId, @PathVariable Long id) {
        List<OrderResponse> responses = orderResponseService.getAll();
        orderResponseService.setPharmacyService(pharmacyService);
        orderResponseService.setMedicineQuantityService(medicineQuantityService);
        orderResponseService.setMedicinePricingService(medicinePricingService);
        for (OrderResponse or : responses) {
            if (!or.getOrder().getId().equals(orderId))
                continue;
            if (or.getId().equals(id))
                orderResponseService.accept(or);
            else
                orderResponseService.decline(or);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        List<OrderResponse> responses = orderResponseService.getAllByOrderId(id);
        if (responses.size() != 0)
            return new ResponseEntity<>("Narudzbenica vec ima ponude", HttpStatus.BAD_REQUEST);
        BulkOrder bo = bulkOrderService.getOneById(id);
        bulkOrderService.delete(bo);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
