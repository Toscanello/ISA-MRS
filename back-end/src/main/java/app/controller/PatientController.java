package app.controller;

import app.domain.Patient;
import app.domain.Pharmacy;
import app.dto.PatientDTO;
import app.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getPatients() {
        return new ResponseEntity<List<Patient>>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> searchPatients(@RequestParam Map<String, String> search){
        System.out.println(search);
        List<Patient>patients = service.searchPatients(search);

        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient p : patients) {
            patientDTOS.add(new PatientDTO(p));
        }
        return new ResponseEntity<>(patientDTOS,HttpStatus.OK);
    }

    @GetMapping(path = "/pharmacies/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pharmacy> getPharmacy(@PathVariable String regNo) {
        Pharmacy p = service.getPharmacy(regNo);
        if (p != null)
            return new ResponseEntity<Pharmacy>(p, HttpStatus.OK);
        else
            return new ResponseEntity<Pharmacy>(p, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> findAllStudentsByPharmacistEmail(@PathVariable String email){
        List<Patient>patients = service.findAllPatientsByPharmacistEmail(email);

        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient p : patients) {
            patientDTOS.add(new PatientDTO(p));
        }
        return new ResponseEntity<>(patientDTOS,HttpStatus.OK);
    }
}
