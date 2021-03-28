package app.controller;

import app.domain.DPharmacy;
import app.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;
    @GetMapping(value = "/all")
    public ResponseEntity<List<DPharmacy>> getAllStudents() {
        List<DPharmacy> pharmacies = pharmacyService.findAll();
        int s = 0;
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }
}
