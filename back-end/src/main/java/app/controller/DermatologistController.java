package app.controller;

import app.domain.*;
import app.dto.AppointmentDTO;
import app.dto.DetailedDermatologistDTO;
import app.dto.FreeAppointmentDTO;
import app.dto.SimpleDermatologistDTO;
import app.service.*;
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
@RequestMapping(value = "api/dermatologists")
public class DermatologistController {
    @Autowired
    DermatologistService dermatologistService;
    @Autowired
    DermatologistAppointmentService dermatologistAppointmentService;
    @Autowired
    PharmacyService pharmacyService;
    @Autowired
    DermatologistWorkHourService workHourService;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PatientService patientService;

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
        List<DermatologistWorkHour> dermatologistWorkHours
                = workHourService.getDermatologistWorkHours(newAppointment.getDermatologistEmail());

        System.out.println(newAppointment.getBegin());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime appointmentBeginLDT = LocalDateTime.parse(newAppointment.getBegin().trim(), formatter);

        LocalTime appointmentDuration = LocalTime.parse(newAppointment.getDuration());
        LocalTime appointmentBegin = appointmentBeginLDT.toLocalTime();

        int appointmentDurationInMinutes = appointmentDuration.getHour()*60 + appointmentDuration.getMinute();
        LocalTime appointmentEnd = appointmentBegin.plusMinutes(appointmentDurationInMinutes);
        LocalDateTime appointmentEndLDT = appointmentBeginLDT.plusMinutes(appointmentDurationInMinutes);

        /* Check if the dermatologist works at the time of appointment */
        for (DermatologistWorkHour wh : dermatologistWorkHours) {
            if (!wh.getPharmacy().getRegNo().equals(newAppointment.getPharmacyRegNo()))
                continue;   /* No need to check other pharmacy work hours */
            if (wh.getBegginingHour().isAfter(appointmentBegin) || wh.getEndingHour().isBefore(appointmentEnd)) {
                return new ResponseEntity<>("Dermatologist isn't in office at that hour", HttpStatus.OK);
            }
        }

        /* Check if dermatologist has other appointments at specified time */
        List<Appointment> dermatologistAppointments
                = appointmentService.getAllAppointments(newAppointment.getDermatologistEmail());
        for (Appointment ap : dermatologistAppointments) {
            if (ap.getStartTime().isBefore(appointmentEndLDT) && ap.getEndTime().isAfter(appointmentBeginLDT)) {
                return new ResponseEntity<>("Dermatologist already has a scheduled appointment at that time", HttpStatus.OK);
            }
        }
        Pharmacy p = pharmacyService.getPharmacy(newAppointment.getPharmacyRegNo());
        Dermatologist d = dermatologistService.findDermatologist(newAppointment.getDermatologistEmail());
        DermatologistAppointment da = new DermatologistAppointment(
                appointmentBeginLDT,
                appointmentDuration,
                d,
                p,
                newAppointment.getPrice()
        );
        dermatologistAppointmentService.save(da);
        return new ResponseEntity<>("Successfully added a new appointment", HttpStatus.OK);
    }

    @GetMapping(path = "/appointments/{email}/{pharmacy}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByDermatologist(@PathVariable String email,@PathVariable String pharmacy){
        List<Appointment> appointments = appointmentService.findActiveAppointmentsByDermatologist(email,pharmacy);

        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment a : appointments) {
            appointmentDTOS.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @GetMapping(path = "/dermAppointments/{email}/{pharmacy}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FreeAppointmentDTO>> findAllDermAppointmentsByDermatologist(@PathVariable String email,@PathVariable String pharmacy){
        List<DermatologistAppointment> appointments = dermatologistAppointmentService.findActiveAppointmentsByDermatologist(email,pharmacy);

        List<FreeAppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (DermatologistAppointment a : appointments) {
            appointmentDTOS.add(new FreeAppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/list/pharmacy/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<DetailedDermatologistDTO>>
    getPharmacyDetailedDermatologists(@PathVariable String regNo) {
        List<Dermatologist> dermatologists = dermatologistService.findPharmacyDermatologists(regNo);
        return createDetailedDermatologistDTO(dermatologists);
    }

    @GetMapping(value = "/list/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<DetailedDermatologistDTO>> getAllPharmacists() {
        List<Dermatologist> dermatologists = dermatologistService.findAll();
        return createDetailedDermatologistDTO(dermatologists);
    }

    @DeleteMapping(value = "/delete/employment/{regNo}/{email}")
    public ResponseEntity<String>
    deleteDermatologistFromPharmacy(@PathVariable String regNo, @PathVariable String email) {
        List<Appointment> activeAppointments = appointmentService.findActiveAppointmentsByDermatologist(email, regNo);
        if (activeAppointments.size() != 0)
            return new ResponseEntity<>("Dermatologist has active appointments", HttpStatus.BAD_REQUEST);
        pharmacyService.deleteDermatologistEmploymentFromPharmacy(regNo, email);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping(value = "/scheduleNewAppointment/{id}/{email}")
    public ResponseEntity<String> scheduleNewAppointment(@PathVariable Long id, @PathVariable String email){
        DermatologistAppointment da = dermatologistAppointmentService.findById(id);
        Patient p = patientService.findOneByEmail(email);
        boolean check=appointmentService.createNewAppointment(da,p);
        if(!check)
            return new ResponseEntity<>("Patient has appointment in that time", HttpStatus.BAD_REQUEST);
        dermatologistAppointmentService.delete(da);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public ResponseEntity<Set<DetailedDermatologistDTO>>
    createDetailedDermatologistDTO(List<Dermatologist> dermatologists) {
        Set<DetailedDermatologistDTO> toReturn = new HashSet<>();
        for (Dermatologist d : dermatologists) {
            DetailedDermatologistDTO dermatologistDTO = new DetailedDermatologistDTO(d);
            List<Pharmacy> dermatologistPharmacies = pharmacyService.getPharmaciesByDermatologist(d.getEmail());
            for (Pharmacy dermatologistPharmacy: dermatologistPharmacies) {
                dermatologistDTO.getPharmacyNames().add(dermatologistPharmacy.getName());
                dermatologistDTO.getPharmacyRegNos().add(dermatologistPharmacy.getRegNo());
            }
            toReturn.add(dermatologistDTO);
        }

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }


}
