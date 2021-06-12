package app.controller;

import app.service.PharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/pharmacyAdmin")
public class PharmacyAdminController {
    @Autowired
    private PharmacyAdminService pharmacyAdminService;

}
