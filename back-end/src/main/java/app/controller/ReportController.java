package app.controller;

import app.domain.MedicineQuantity;
import app.domain.Patient;
import app.domain.Report;
import app.dto.ReportDTO;
import app.service.PatientService;
import app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @Autowired
    PatientService patientService;
    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addReport(@RequestBody ReportDTO reportDTO){
        Patient patient = patientService.findOneByEmail(reportDTO.getPatient_id());
        Report report = new Report(reportDTO.getReport(), LocalDateTime.now(),reportDTO.getDays(),patient);
        reportService.save(report);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
