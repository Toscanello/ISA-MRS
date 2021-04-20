package app.controller;

import app.domain.Appointment;
import app.domain.Pharmacist;
import app.domain.WorkHour;
import app.dto.FreeAppointmentDTO;
import app.dto.FreeAppointmentPatientDTO;
import app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PharmacyService pharmacyService;
    @Autowired
    PharmacistService pharmacistService;
    @Autowired
    PatientService patientService;

    @PostMapping(value="/addAppointment",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAppointment(@RequestBody FreeAppointmentPatientDTO newAppointment){
        Pharmacist pharmacist = pharmacistService.findPharmacistByEmail(newAppointment.getDermatologistEmail());
        System.out.println(pharmacist.getName());
        WorkHour wh = pharmacist.getWorkHour();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime appointmentBeginLDT = LocalDateTime.parse(newAppointment.getBegin().trim(), formatter);

        LocalTime appointmentDuration = LocalTime.parse(newAppointment.getDuration());
        LocalTime appointmentBegin = appointmentBeginLDT.toLocalTime();

        int appointmentDurationInMinutes = appointmentDuration.getHour()*60 + appointmentDuration.getMinute();
        LocalTime appointmentEnd = appointmentBegin.plusMinutes(appointmentDurationInMinutes);
        LocalDateTime appointmentEndLDT = appointmentBeginLDT.plusMinutes(appointmentDurationInMinutes);

        if (wh.getStart().isAfter(appointmentBegin) || wh.getEnd().isBefore(appointmentEnd)){
            return new ResponseEntity<>("Pharmacist isn't in office at that hour", HttpStatus.OK);
        }

        List<Appointment> appointmentList = appointmentService.getAllAppointments(newAppointment.getDermatologistEmail());
        for(Appointment a: appointmentList){
            if(a.getStartTime().isBefore(appointmentEndLDT)&&a.getEndTime().isAfter(appointmentBeginLDT)){
                return new ResponseEntity<>("Pharmacist already has a scheduled appointment", HttpStatus.OK);
            }
        }

        appointmentList = appointmentService.getAllAppointmentsByPatientId(newAppointment.getPatientEmail());
        for(Appointment a: appointmentList){
            if(a.getStartTime().isBefore(appointmentEndLDT)&&a.getEndTime().isAfter(appointmentBeginLDT)){
                return new ResponseEntity<>("Patient already has a scheduled appointment", HttpStatus.OK);
            }
        }

        Appointment ap = new Appointment(appointmentBeginLDT,appointmentEndLDT,patientService.findOneByEmail(newAppointment.getPatientEmail()),pharmacist, newAppointment.getPrice());
        appointmentService.save(ap);
        return new ResponseEntity<>("Successfully added a new appointment", HttpStatus.OK);
    }

}
