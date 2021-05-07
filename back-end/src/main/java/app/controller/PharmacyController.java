package app.controller;

import app.domain.*;
import app.dto.PharmacistDTO;
import app.dto.SimpleDermatologistDTO;
import app.dto.SimplePharmacyDTO;
import app.service.PharmacistService;
import app.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacistService pharmacistService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<SimplePharmacyDTO>> getAllStudents() {
        List<Pharmacy> pharmacies = pharmacyService.findAll();
        List<SimplePharmacyDTO> pharmaciesDTO = new ArrayList<>();

        for (Pharmacy p : pharmacies)
        {
            pharmaciesDTO.add(new SimplePharmacyDTO(p));
        }
        return new ResponseEntity<>(pharmaciesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimplePharmacyDTO> getPharmacy(@PathVariable String regNo) {
        Pharmacy p = pharmacyService.getPharmacy(regNo);
        HttpStatus status = p == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        SimplePharmacyDTO dto = p == null ? null : new SimplePharmacyDTO(p);
        return new ResponseEntity<>(dto, status);
    }

    @GetMapping(value = "apoteke/{date}_{time}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SimplePharmacyDTO>> gettralal(@PathVariable String date, @PathVariable String time) {
        //sastanak traje 30 minuta
        String str = date + " " + time; //"2019-05-04 09:10";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        LocalTime start = LocalTime.parse(time);
        LocalTime end = start.plusMinutes(30); //svaki sastanak sa farmaceutom traje 30 minuta
        List<Pharmacy> pharmacies = pharmacyService.getPharmaciesByTime(start, end, dateTime);

        List<SimplePharmacyDTO> pharmaciesDTO = new ArrayList<>();
        for (Pharmacy p : pharmacies)
        {
            pharmaciesDTO.add(new SimplePharmacyDTO(p));
        }
        return new ResponseEntity<>(pharmaciesDTO, HttpStatus.OK);
    }
    @GetMapping(value = "pharmacist/{date}_{time}_{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> findPharmacistByWorkHour(@PathVariable String date, @PathVariable String time, @PathVariable String regNo) {
        //sastanak traje 30 minuta
        String str = date + " " + time; //"2019-05-04 09:10";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        LocalTime start = LocalTime.parse(time);
        LocalTime end = start.plusMinutes(30); //svaki sastanak sa farmaceutom traje 30 minuta
        /*List<Pharmacist> pharmacists = pharmacistService.findPharmacist(start, end, dateTime, regNo);
        List<PharmacistDTO> toReturn = new ArrayList<>();
        for (Pharmacist p :pharmacists)
            toReturn.add(new PharmacistDTO(p));*/
        return new ResponseEntity<>(pharmacistService.findPharmacist(start, end, dateTime, regNo), HttpStatus.OK);
    }

    @PutMapping(value = "edit/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimplePharmacyDTO>
    editPharmacy(@PathVariable String regNo, @RequestBody SimplePharmacyDTO editedPharmacy) {
        Pharmacy oldPharmacy = pharmacyService.getPharmacy(editedPharmacy.getRegNo());
        oldPharmacy.setName(editedPharmacy.getName());
        oldPharmacy.getAddress().fromAddress(editedPharmacy.getAddress());  //Using setAddress would put a new Address in DB
        pharmacyService.save(oldPharmacy);  //save changes to database
        return new ResponseEntity<>(editedPharmacy, HttpStatus.OK);
    }

}
