package app.controller;

import app.domain.*;
import app.dto.*;
import app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PharmacyService pharmacyService;
    @Autowired
    PharmacistService pharmacistService;
    @Autowired
    PharmacistRatingService pharmacistRatingService;
    @Autowired
    PatientService patientService;

    @Autowired
    private JavaMailSender emailSender;

    @PreAuthorize("hasRole('PHARMACIST')")
    @PostMapping(value="/addAppointment",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAppointment(@RequestBody FreeAppointmentPatientDTO newAppointment){
        Patient patient = patientService.findOneByEmail(newAppointment.getPatientEmail());
        if(patient.getPenalty()>3)
            return new ResponseEntity<>("Patient has more than 3 penalties.", HttpStatus.OK);
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

        Appointment ap = new Appointment(appointmentBeginLDT,appointmentEndLDT,patientService.findOneByEmail(newAppointment.getPatientEmail()),pharmacist,
                pharmacist.getPharmacy(), newAppointment.getPrice(), false,false,false);
        appointmentService.save(ap);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("servis.apoteka@gmail.com");
        message.setTo(newAppointment.getPatientEmail());
        message.setSubject("New appointment");
        message.setText("Yeah");
        emailSender.send(message);
        return new ResponseEntity<>("Successfully added a new appointment", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PHARMACIST')")
    @GetMapping(path = "/appointments/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByPharmacistEmail(@PathVariable String email){

        List<Appointment> appointments = appointmentService.findActiveAppointmentsByPharmacistId(email);

        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment a : appointments) {
            appointmentDTOS.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @GetMapping(path = "/list/pharmacy/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PharmacistDTO>>
    findAllPharmacistsByPharmacy(@PathVariable String regNo) {
        List<Pharmacist> pharmacyPharmacists = pharmacistService.findPharmacistsByPharmacy(regNo);
        return createPharmacistsDTO(pharmacyPharmacists);
    }

    @GetMapping(path = "/list/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PharmacistDTO>> findAllPharmacists() {
        List<Pharmacist> pharmacyPharmacists = pharmacistService.findAll();
        return createPharmacistsDTO(pharmacyPharmacists);
    }

    @GetMapping(path = "/list/unemployed", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PharmacistDTO>> findUnemployedPharmacists() {
        List<Pharmacist> pharmacyPharmacists = pharmacistService.getUnemployed();
        return createPharmacistsDTO(pharmacyPharmacists);
    }

    @DeleteMapping(value = "/delete/employment/{email}")
    public ResponseEntity<String>
    deleteDermatologistFromPharmacy(@PathVariable String email) {
        List<Appointment> activeAppointments = appointmentService.findActiveAppointmentsByPharmacistId(email);
        if (activeAppointments.size() != 0) {
            for (Appointment ap : activeAppointments) {
                if (ap.getEndTime().isAfter(LocalDateTime.now()))
                    return new ResponseEntity<>("Pharmacist has active appointments", HttpStatus.BAD_REQUEST);
            }
        }
        Pharmacist toBeFired = pharmacistService.findPharmacistByEmail(email);
        toBeFired.setPharmacy(null);
        toBeFired.setWorkHour(null);
        pharmacistService.save(toBeFired);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(path = "/pharm/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalWorkerDTO> getPharmacistByEmail(@PathVariable String email){

        Pharmacist pharmacist = pharmacistService.findPharmacistByEmail(email);
        MedicalWorkerDTO medicalWorkerDTO = new MedicalWorkerDTO(pharmacist);
        return new ResponseEntity<>(medicalWorkerDTO,HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{email}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>
    editPatient(@PathVariable String email, @RequestBody MedicalWorkerDTO editedPharmacist) {

        Pharmacist pharmacist = pharmacistService.findPharmacistByEmail(email);
        pharmacistService.edit(pharmacist, editedPharmacist);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping(value = "/employ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEmployment(@RequestBody WorkHourDTO workHourDTO) {
        Pharmacy ph = pharmacyService.getPharmacy(workHourDTO.getPharmacyRegNo());

        WorkHour wh = new WorkHour();
        wh.setPharmacy(ph);
        wh.setStart(workHourDTO.getStartTimeAsTime());
        wh.setEnd(workHourDTO.getEndTimeAsTime());

        Pharmacist newEmployee = pharmacistService.findPharmacistByEmail(workHourDTO.getWorkerEmail());
        newEmployee.setWorkHour(wh);
        newEmployee.setPharmacy(ph);
        pharmacistService.save(newEmployee);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    public ResponseEntity<Set<PharmacistDTO>>
    createPharmacistsDTO(List<Pharmacist> pharmacists) {
        Set<PharmacistDTO> toRet = new HashSet<>();
        for (Pharmacist p : pharmacists)
            toRet.add(new PharmacistDTO(p));
        return new ResponseEntity<>(toRet, HttpStatus.OK);
    }

    @GetMapping(path = "/getPharmacy/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPharmacy(@PathVariable String email){
        Pharmacist pharmacist = pharmacistService.findPharmacistByEmail(email);
        return new ResponseEntity<String>(pharmacist.getPharmacy().getRegNo(),HttpStatus.OK);
    }

    @GetMapping(path = "/pharmacy/rating/{patientEmail}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PharmacistDTO>> getPharmacistForRating(@PathVariable String patientEmail){
       Set<Pharmacist> pharmacists = pharmacistService.findPharmacistForRating(patientEmail);
        Set<PharmacistDTO> toRet = new HashSet<>();
        for (Pharmacist p : pharmacists)
            toRet.add(new PharmacistDTO(p));
        return new ResponseEntity<Set<PharmacistDTO>>(toRet,HttpStatus.OK);
    }

    @GetMapping(path = "/ratings/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<MedicalWorkerRatingDTO>>
    getPharmacistRatingsByPharmacy(@PathVariable String regNo) {
        List<Pharmacist> allPharmacists = pharmacistService.findPharmacistsByPharmacy(regNo);
        Set<MedicalWorkerRatingDTO> ratingSet = new HashSet<>();
        for (Pharmacist pharmacist : allPharmacists) {
            double rating = pharmacistRatingService
                    .calculateRatingByPharmacist(pharmacist.getEmail());
            MedicalWorkerRatingDTO ratingObject = new MedicalWorkerRatingDTO();
            ratingObject.setMedicalWorkerEmail(pharmacist.getEmail());
            ratingObject.setRating(rating);
            ratingSet.add(ratingObject);
        }
        return new ResponseEntity<>(ratingSet, HttpStatus.OK);
    }

    @GetMapping(path = "/all-ratings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<MedicalWorkerRatingDTO>> getPharmacistRatingsByPharmacy() {
        List<Pharmacist> allPharmacists = pharmacistService.findAll();
        Set<MedicalWorkerRatingDTO> ratingSet = new HashSet<>();
        for (Pharmacist pharmacist : allPharmacists) {
            double rating = pharmacistRatingService
                    .calculateRatingByPharmacist(pharmacist.getEmail());
            MedicalWorkerRatingDTO ratingObject = new MedicalWorkerRatingDTO();
            ratingObject.setMedicalWorkerEmail(pharmacist.getEmail());
            ratingObject.setRating(rating);
            ratingSet.add(ratingObject);
        }
        return new ResponseEntity<>(ratingSet, HttpStatus.OK);
    }
}
