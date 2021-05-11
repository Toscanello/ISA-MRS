package app.controller;

import app.domain.Medicine;
import app.domain.MedicinePricing;
import app.domain.MedicineQuantity;
import app.domain.Pharmacy;
import app.dto.MedicineOrderDTO;
import app.dto.MedicinePricingDTO;
import app.dto.SimpleMedicineDTO;
import app.dto.SimplePharmacyDTO;
import app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/medicine")
public class MedicineController {
    @Autowired
    MedicineService medicineService;

    @Autowired
    MedicineQuantityService medicineQuantityService;

    @Autowired
    MedicinePricingService medicinePricingService;

    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    MedicineOrderService medicineOrderService;

    @GetMapping(value = "/pharmacy/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SimpleMedicineDTO>> getMedicineByPharmacy(@PathVariable String regNo) {
        List<Medicine> pharmacyMedicine = medicineService.findMedicineByPharmacy(regNo);
        List<SimpleMedicineDTO> pharmacyMedicineDTOs = new ArrayList<>();
        for (Medicine m : pharmacyMedicine)
            pharmacyMedicineDTOs.add(new SimpleMedicineDTO(m));
        return new ResponseEntity<>(pharmacyMedicineDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/one/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleMedicineDTO> getMedicineByCode(@PathVariable String code) {
        return new ResponseEntity<>(new SimpleMedicineDTO(medicineService.findMedicineByCode(code)), HttpStatus.OK);
    }

    @GetMapping(value = "/pricings/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicinePricingDTO>> getPricingsByPharmacy(@PathVariable String regNo) {
        List<MedicinePricing> pharmacyPricings = medicinePricingService.findAllPricingsByPharmacyRegNo(regNo);
        List<MedicinePricingDTO> medicinePricingDTOs = new ArrayList<>();
        for (MedicinePricing mp : pharmacyPricings)
            medicinePricingDTOs.add(new MedicinePricingDTO(mp));
        return new ResponseEntity<>(medicinePricingDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/pricing/{regNo}/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicinePricingDTO>>
    getPricingsForMedicineByPharmacy(@PathVariable String regNo, @PathVariable String code) {
        List<MedicinePricing> pricings = medicinePricingService.findAllPricingsForMedicineInPharmacy(regNo, code);
        List<MedicinePricingDTO> pricingDTOS = new ArrayList<>();
        for (MedicinePricing mp : pricings)
            pricingDTOS.add(new MedicinePricingDTO(mp));
        return new ResponseEntity<>(pricingDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/set/pricing/{regNo}/{code}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicinePricingDTO>
    setNewPricing(@PathVariable String regNo, @PathVariable String code, @RequestBody MedicinePricingDTO newPricingDTO) {
        MedicinePricing currentPricing = medicinePricingService.findActivePricingForMedicineInPharmacy(regNo, code);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        currentPricing.setPricingEnd(LocalDateTime.parse(newPricingDTO.getPricingStart(), formatter));
        medicinePricingService.save(currentPricing);

        Medicine pricingMed = medicineService.findMedicineByCode(code);
        Pharmacy pricingPharma = pharmacyService.getPharmacy(regNo);
        MedicinePricing newPricing = new MedicinePricing();
        newPricing.setPrice(newPricingDTO.getPrice());
        newPricing.setMedicine(pricingMed);
        newPricing.setPharmacy(pricingPharma);
        newPricing.setPricingStart(LocalDateTime.parse(newPricingDTO.getPricingStart(), formatter));
        newPricing.setPricingEnd(null);
        medicinePricingService.save(newPricing);
        return new ResponseEntity<>(newPricingDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/pricing/{regNo}/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePricing(@PathVariable String regNo, @PathVariable String code) {
        //medicinePricingService.deletePricingFromPharmacy(regNo, code);
        medicineQuantityService.deleteMedicineQuantityByPharmacy(regNo, code);
        return new ResponseEntity<>("Uspešno obrisan lek iz apoteke", HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicinePricingDTO>>
    getMedicinePricingsDate() {
        List<MedicinePricing> pricings = medicinePricingService.findMedicinePricingsByDate();
        List<MedicinePricingDTO> pricingDTOS = new ArrayList<>();
        for (MedicinePricing mp : pricings)
            pricingDTOS.add(new MedicinePricingDTO(mp));
        return new ResponseEntity<>(pricingDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/order/{userEmail}/{quantity}/{endTime}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>
    addNewOrder(@PathVariable String userEmail, @PathVariable int quantity,  @PathVariable String endTime, @RequestBody MedicinePricingDTO medicinePricingDTO) {

        Set<String> medIds = medicinePricingService.findMedicineIDByMedicinePricingID(medicinePricingDTO.getId());

        String medId = medIds.iterator().next();
        String regNo = medicinePricingDTO.getPharmacyDTO().getRegNo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");

        int medicineQuantity = medicineQuantityService.findMedicineQuantityByPharmacyRegNoAndMedicineCode(regNo, medicinePricingDTO.getMedicineDTO().getCode());
        medicineQuantityService.updateMedicineQuantityByPharmacyRegNoAndMedicineCode(regNo, medId, medicineQuantity-quantity);

        medicineOrderService.insertNewOrder(medicinePricingDTO.getId(),quantity,quantity*medicinePricingDTO.getPrice(),
                userEmail, LocalDateTime.now(), LocalDateTime.parse(endTime, formatter));

        return new ResponseEntity<>("mq", HttpStatus.OK);
    }

    @GetMapping(value = "/quantity/{regNo}/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer>
    getMedicineQuantityByPharmacyRegNoAndMedicineCode(@PathVariable String regNo, @PathVariable String code) {
        int quantity = medicineQuantityService.findMedicineQuantityByPharmacyRegNoAndMedicineCode(regNo, code);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }
}
