package app.controller;

import app.domain.Medicine;
import app.domain.bulk_order.BulkOrder;
import app.domain.bulk_order.BulkOrderItem;
import app.dto.bulk_order.BulkOrderDTO;
import app.dto.bulk_order.BulkOrderItemDTO;
import app.repository.MedicineRepository;
import app.service.MedicineService;
import app.service.PharmacyService;
import app.service.bulk_order.BulkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping("/api/order")
public class BulkOrderController {
    @Autowired
    private BulkOrderService bulkOrderService;
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
}
