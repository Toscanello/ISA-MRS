package app.controller;

import app.domain.*;
import app.dto.FreeAppointmentDTO;
import app.dto.SimpleDermatologistDTO;
import app.repository.AppointmentRepository;
import app.service.AppointmentService;
import app.service.DermatologistAppointmentService;
import app.service.DermatologistService;
import app.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/dermatologists")
public class DermatologistController {
    @Autowired
    DermatologistService dermatologistService;
    @Autowired
    DermatologistAppointmentService dermatologistAppointmentService;
    @Autowired
    PharmacyService pharmacyService;

    @GetMapping(value = "/pharmacy/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<SimpleDermatologistDTO>>
    getPharmacyDermatologists(@PathVariable String regNo) {
        List<Dermatologist> dermatologists = dermatologistService.findPharmacyDermatologists(regNo);
        Set<SimpleDermatologistDTO> toReturn = new HashSet<>();
        for (Dermatologist d :dermatologists)
            toReturn.add(new SimpleDermatologistDTO(d));

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PostMapping(value = "/addAppointment",
    consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAppointment(@RequestBody FreeAppointmentDTO newAppointment) {
        List<WorkHour> dermatologistWorkHours
                = dermatologistService.getDermatologistWorkHours(newAppointment.getDermatologistEmail());
        boolean available = true;
        LocalTime appointmentDuration = newAppointment.getDuration();
        LocalTime appointmentBegin = newAppointment.getBegin().toLocalTime();
        int appointmentDurationInMinutes = appointmentDuration.getHour()*60 + appointmentDuration.getMinute();
        LocalTime appointmentEnd = appointmentBegin.plusMinutes(appointmentDurationInMinutes);

        for (WorkHour wh : dermatologistWorkHours) {
            if (!wh.getPharmacy().getRegNo().equals(newAppointment.getPharmacyRegNo()))
                continue;
            if (wh.getBegginingHour().isAfter(appointmentBegin) || wh.getEnding_hour().isBefore(appointmentEnd)) {
                return new ResponseEntity<>("Dermatologist isn't in office at that hour", HttpStatus.NOT_ACCEPTABLE);
            }
        }

        List<Appointment> dermatologistAppointments
                = dermatologistService.getAllAppointments(newAppointment.getDermatologistEmail());
        for (Appointment ap : dermatologistAppointments) {
            if (ap.getStartTime().isAfter(newAppointment.getBegin()) ||
                ap.getEndTime().isBefore(newAppointment.getBegin().plusMinutes(appointmentDurationInMinutes))) {
                return new ResponseEntity<>("Dermatologist already has a scheduled appointment at that time", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        Pharmacy p = pharmacyService.getPharmacy(newAppointment.getPharmacyRegNo());
        Dermatologist d = dermatologistService.findDermatologist(newAppointment.getDermatologistEmail());
        DermatologistAppointment da = new DermatologistAppointment(
                newAppointment.getBegin(),
                newAppointment.getDuration(),
                d,
                p,
                newAppointment.getPrice()
        );
        dermatologistAppointmentService.save(da);
        return new ResponseEntity<>("Successfully added a new appointment", HttpStatus.OK);
    }
}
