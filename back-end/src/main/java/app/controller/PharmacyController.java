package app.controller;

import app.domain.DPharmacy;
import app.domain.Pharmacy;
import app.dto.SimplePharmacyDTO;
import app.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Pharmacy>> getAllStudents() {
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }

    @GetMapping(value = "/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimplePharmacyDTO> getPharmacy(@PathVariable String regNo) {
        Pharmacy p = pharmacyService.getPharmacy(regNo);
        HttpStatus status = p == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        SimplePharmacyDTO dto = p == null ? null : new SimplePharmacyDTO(p);
        return new ResponseEntity<>(dto, status);
    }
}
