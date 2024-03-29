package app.controller;

import app.domain.MedicalWorker;
import app.domain.Pharmacist;
import app.domain.Vaccation;
import app.dto.VaccationDTO;
import app.service.DermatologistService;
import app.service.PharmacistService;
import app.service.VaccationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @PreAuthorize("hasAnyRole('PHARMACIST','DERMATOLOGIST')")
    @PostMapping(value = "/addVaccation",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addVaccation(@RequestBody VaccationDTO vaccationDTO){
        MedicalWorker medicalWorker = pharmacistService.findPharmacistByEmail(vaccationDTO.getMedicalWorkerId());
        if(medicalWorker==null){
            medicalWorker= dermatologistService.findDermatologist(vaccationDTO.getMedicalWorkerId());
        }
        LocalDate startDate = LocalDate.parse(vaccationDTO.getStartDate());
        LocalDate endDate = LocalDate.parse(vaccationDTO.getEndDate());
        if(endDate.isBefore(startDate)){
            startDate = endDate;
            endDate = LocalDate.parse(vaccationDTO.getStartDate());
        }
        Vaccation vaccationCheck = vaccationService.findVacationByMedicalWorkerEmail(medicalWorker.getEmail());
        if(vaccationCheck==null) {
            Vaccation.Status status = Vaccation.Status.valueOf(vaccationDTO.getStatus());

            Vaccation vaccation = new Vaccation(medicalWorker, startDate, endDate, status);
            vaccationService.save(vaccation);
        }
    }

    @GetMapping(value = "/pharmacist/requests/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<VaccationDTO>> getPharmacistVacationRequests(@PathVariable String regNo) {
        List<Pharmacist> pharmacists = pharmacistService.findPharmacistsByPharmacy(regNo);
        Set<VaccationDTO> vacationDTOs = new HashSet<>();
        for (Pharmacist ph : pharmacists) {
            Vaccation pharmacistVacation = vaccationService.findVacationByMedicalWorkerEmail(ph.getEmail());
            if (pharmacistVacation != null)
                vacationDTOs.add(new VaccationDTO(pharmacistVacation));
        }
        return new ResponseEntity<>(vacationDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/pharmacist/request/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VaccationDTO> getPharmacistVacationRequest(@PathVariable String email) {
        Vaccation vacation = vaccationService.findVacationByMedicalWorkerEmail(email);
        return new ResponseEntity<>(new VaccationDTO(vacation), HttpStatus.OK);
    }

    @PutMapping(value = "/pharmacist/approve/request", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> approvePharmacistRequest(@RequestBody HashMap<String, String> vacayResponse) {
        String pharmacistEmail = vacayResponse.get("MedicalWorkerID");
        Vaccation v = vaccationService.findVacationByMedicalWorkerEmail(pharmacistEmail);
        vaccationService.acceptVacationRequest(v, pharmacistEmail);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping(value = "/pharmacist/decline/request", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> declinePharmacistRequest(@RequestBody HashMap<String, String> vacayResponse) {
        String pharmacistEmail = vacayResponse.get("MedicalWorkerID");
        String declineReason = vacayResponse.get("text");
        Vaccation v = vaccationService.findVacationByMedicalWorkerEmail(pharmacistEmail);
        vaccationService.declineVacationRequest(v, pharmacistEmail, declineReason);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
