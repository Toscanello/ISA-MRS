package app.controller;

import app.domain.MedicalWorker;
import app.domain.Vaccation;
import app.dto.VaccationDTO;
import app.service.DermatologistService;
import app.service.PharmacistService;
import app.service.VaccationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;


@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/vaccation")
public class VaccationController {
    @Autowired
    PharmacistService pharmacistService;
    @Autowired
    DermatologistService dermatologistService;
    @Autowired
    VaccationService vaccationService;
    @Autowired
    JavaMailSender emailSender;

    @PostMapping(value = "/addVaccation",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addVaccation(@RequestBody VaccationDTO vaccationDTO){
        MedicalWorker medicalWorker = pharmacistService.findPharmacistByEmail(vaccationDTO.getMedicalWorkerId());
        System.out.println("******************************************************************");
        if(medicalWorker==null){
            medicalWorker= dermatologistService.findDermatologist(vaccationDTO.getMedicalWorkerId());
        }
        LocalDate startDate = LocalDate.parse(vaccationDTO.getStartDate());
        LocalDate endDate = LocalDate.parse(vaccationDTO.getEndDate());
        if(endDate.isBefore(startDate)){
            startDate = endDate;
            endDate = LocalDate.parse(vaccationDTO.getStartDate());
        }
        Vaccation.Status status = Vaccation.Status.valueOf(vaccationDTO.getStatus());

        Vaccation vaccation = new Vaccation(medicalWorker,startDate,endDate,status);
        vaccationService.save(vaccation);
    }
    
}
