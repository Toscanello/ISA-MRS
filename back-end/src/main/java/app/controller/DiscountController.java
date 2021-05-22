package app.controller;

import app.domain.Discount;
import app.domain.Medicine;
import app.domain.MedicineQuantity;
import app.domain.Pharmacy;
import app.dto.DiscountDTO;
import app.service.DiscountService;
import app.service.MedicineQuantityService;
import app.service.MedicineService;
import app.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping("/api/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;
    @Autowired
    MedicineService medicineService;
    @Autowired
    MedicineQuantityService medicineQuantityService;
    @Autowired
    PharmacyService pharmacyService;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addDiscount(@RequestBody DiscountDTO newDiscount) {
        Discount d = new Discount();
        Pharmacy pharmacy = pharmacyService.getPharmacy(newDiscount.getPharmacyRegNo());
        MedicineQuantity medicineQuantity = medicineQuantityService
                .findMedicineQuantityByPharmacyRegNo(pharmacy.getRegNo(), newDiscount.getMedicineCode());
        if (medicineQuantity == null) {
            return new ResponseEntity<>("Pharmacy doesn't have that medicine", HttpStatus.NOT_FOUND);
        }

        List<Discount> previousDiscounts
                = discountService.findAllByMedicineAndPharmacy(newDiscount.getMedicineCode(), pharmacy.getRegNo());

        for (Discount previousDiscount : previousDiscounts) {
            if (newDiscount.getStartDateAsDate().isBefore(previousDiscount.getEndDate())
                    && newDiscount.getEndDateAsDate().isAfter(previousDiscount.getStartDate())) {
                return new ResponseEntity<>("A discount in that period already exists", HttpStatus.BAD_REQUEST);
            }
        }

        Medicine med = medicineService.findMedicineByCode(newDiscount.getMedicineCode());
        d.setMedicine(med);
        d.setPharmacy(pharmacy);
        d.setPercentage(newDiscount.getPercentage());
        d.setStartDate(newDiscount.getStartDateAsDate());
        d.setEndDate(newDiscount.getEndDateAsDate());
        discountService.save(d);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
