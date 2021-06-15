package app.controller;

import app.domain.*;
import app.dto.*;
import app.repository.MedicineOrderRepository;
import app.repository.MedicineRatingRespository;
import app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService service; //findByEmail

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    private MedicineOrderService medicineOrderService;

    @Autowired
    private MedicineQuantityService medicineQuantityService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private MedicineRatingService medicineRatingService;

    @Autowired
    private DermatologistRatingService dermatologistRatingService;

    @Autowired
    private PharmacistRatingService pharmacistRatingService;

    @PreAuthorize("hasAnyRole('PHARMACIST','DERMATOLOGIST')")
    @GetMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> searchPatients(@RequestParam Map<String, String> search){

        List<Patient> patients = service.searchPatients(search);

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
    public ResponseEntity<List<PatientDTO>> findAllStudentsByMedicalEmail(@PathVariable String email){

        List<Patient>patients = service.findAllPatientsByMedicalEmail(email);
        List<PatientDTO> patientDTOS = new ArrayList<>();

        for (Patient p : patients) {
            PatientDTO patient=new PatientDTO(p);
            List<Appointment> appointmentList=appointmentService.findActiveAppointmentsByPatientId(p.getEmail());
            LocalDateTime now = LocalDateTime.now();
            for(Appointment a :appointmentList) {
                if(!a.getMedicalWorker().getEmail().equals(email))
                    continue;
                long diff = Math.abs(ChronoUnit.MINUTES.between(a.getStartTime(),now));
                if (diff <= 15 && !a.isFinished()) {
                    patient.setCheck(true);
                    break;
                }
            }
            patientDTOS.add(patient);
        }
        return new ResponseEntity<>(patientDTOS,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/appointments/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByPharmacistEmail(@PathVariable String email){

        List<Appointment> appointments = appointmentService.findActiveAppointmentsByPatientId(email);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        for (Appointment a : appointments) {
            appointmentDTOS.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/cancelAppointment",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cancelAppointment(@RequestBody AppointmentDTO appointment){

        Appointment appo = appointmentService.findOneById(appointment.getId());
        appointmentService.cancelAppointment(appointment.getId());
        dermatologistAppointmentService.saveNewAppointment(appo, dermatologistService.findDermatologist(appo.getMedicalWorker().getEmail()));
        return new ResponseEntity<>("Canceled appointment", HttpStatus.OK);
    }

    @GetMapping(path = "/patient/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> getPatientByEmail(@PathVariable String email){

        Patient patient = service.findOneByEmail(email);
        PatientDTO patientDTO = new PatientDTO(patient);
        return new ResponseEntity<>(patientDTO,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping(value = "edit/{email}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO>
    editPatient(@PathVariable String email, @RequestBody PatientDTO editedPatient) {

        Patient patient = service.findOneByEmail(email);
        service.save(patient, editedPatient);
        return new ResponseEntity<>(editedPatient, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/orders/patient/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicineOrderDTO>> getPatientsOrders(@PathVariable String email){

        List<MedicineOrder> orders = medicineOrderService.findPatientsMedicineOrders(email);
        List<MedicineOrderDTO> orderDTOS = new ArrayList<>();

        for (MedicineOrder mo : orders) {
            orderDTOS.add(new MedicineOrderDTO(mo));
        }
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping(value = "add/order/{email}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>
    newOrder(@PathVariable String email, @RequestBody PatientDTO editedPatient) {
        return new ResponseEntity<>("editedPatient", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "delete/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>
    deleteOrder(@RequestBody MedicineOrderDTO medicineOrder) {
        MedicineOrder mo = medicineOrderService.findOrderById(medicineOrder.getId());
        medicineQuantityService.medicineOrderCancellation(mo,
                medicineOrder.getMedicine().getPharmacyDTO().getRegNo(), medicineOrder.getMedicine().getMedicineDTO().getCode());
        int i = 0;
        medicineOrderService.delete(mo);
        return new ResponseEntity<>("deleteOrder", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/appointments/dermatologist/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDTO>> getDermatologistAppointments(@PathVariable String email){
        List<Appointment> appointments = appointmentService.findPatientsAppointments(email);
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

        for (Appointment a : appointments) {
            appointmentDTOs.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/appointments/pharmacist/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDTO>> getPharmacistAppointments(@PathVariable String email){
        List<Appointment> appointments = appointmentService.findPatientsPharmacistAppointments(email);
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

        for (Appointment a : appointments) {
            appointmentDTOs.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/advertising/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SimplePharmacyDTO>> getPatientsPharmacies(@PathVariable String email){

        List<Pharmacy> pharmacies = pharmacyService.getPharmacyAdvertising(email);

        List<SimplePharmacyDTO> pharmaciesDTO = new ArrayList<>();
        for (Pharmacy p : pharmacies)
        {
            pharmaciesDTO.add(new SimplePharmacyDTO(p));
        }
        return new ResponseEntity<>(pharmaciesDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('PHARMACIST','DERMATOLOGIST')")
    @PostMapping(path = "/finished/{email}/{emailMed}/{appearance}")
    public void finished(@PathVariable String email,@PathVariable String emailMed,@PathVariable Boolean appearance){
        Patient patient = service.findOneByEmail(email);
        List<Appointment> appointmentList=appointmentService.findActiveAppointmentsByPatientId(patient.getEmail());
        LocalDateTime now = LocalDateTime.now();
        for(Appointment a :appointmentList) {
            if(!a.getMedicalWorker().getEmail().equals(emailMed))
                continue;
            long diff = Math.abs(ChronoUnit.MINUTES.between(a.getStartTime(),now));
            if (diff <= 15 && !a.isFinished()) {
                appointmentService.updateFinished(a.getId());
                if(appearance)
                    appointmentService.updateAppearance(a.getId());
                break;
            }
        }
    }
    @PreAuthorize("hasAnyRole('PHARMACIST','DERMATOLOGIST')")
    @PutMapping(path = "/penalty/{id}")
    public void penalty(@PathVariable Long id){

        service.addPenalty(appointmentService.findOneById(id).getPatient());
    }
    @PreAuthorize("hasAnyRole('PHARMACIST','DERMATOLOGIST')")
    @PutMapping(path = "/penalty/user/{email}")
    public void penaltyUser(@PathVariable String email){
        service.addPenalty(service.findOneByEmail(email));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/allergy/{email}/{code}")
    public ResponseEntity<String> addAllergy(@PathVariable String email, @PathVariable String code){
        service.insertAllergy(email, code);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "my/allergies/{email}")
    public ResponseEntity<Set<SimpleMedicineDTO>> patientsAllergies(@PathVariable String email){
        List<Medicine> allAllergies = medicineService.findPatientsAllergies(email);
        Set<SimpleMedicineDTO> allergies = new HashSet<>();
        for (Medicine m : allAllergies) {
            allergies.add(new SimpleMedicineDTO(m));
        }
        return new ResponseEntity<>(allergies, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "delete/allergy/{email}/{code}")
    public ResponseEntity<String>
    deleteAllergyMethod(@PathVariable String email, @PathVariable String code) {
        service.deleteAllergy(email, code);
        return new ResponseEntity<>("deletedAllergy", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/medicine/rating/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SimpleMedicineDTO>> getMedicineForRating(@PathVariable String email){

        Set<Medicine> medicines = medicineService.findMedicineForRating(email);
        List<SimpleMedicineDTO> medicineDTOS = new ArrayList<>();

        for (Medicine m : medicines) {
            medicineDTOS.add(new SimpleMedicineDTO(m));
        }
        return new ResponseEntity<>(medicineDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "get/medicine/rating/{email}/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getMedicineRating(@PathVariable String email, @PathVariable String code){

        return new ResponseEntity<>(medicineRatingService.getRating(email, code), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/medicine/rate/{email}/{code}/{rating}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> rateMedicine(@PathVariable String email,
                                               @PathVariable String code, @PathVariable Double rating){

        medicineRatingService.addRating(email, code, rating);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "get/pharmacist/rating/{email}/{pharmEmail}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getPharmacistRating(@PathVariable String email, @PathVariable String pharmEmail){

        return new ResponseEntity<>(pharmacistRatingService.getRating(email, pharmEmail), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/pharmacist/rate/{email}/{pharmEmail}/{rating}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> ratePharmacist(@PathVariable String email,
                                               @PathVariable String pharmEmail, @PathVariable Double rating){

        pharmacistRatingService.addRating(email, pharmEmail, rating);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "get/dermatologist/rating/{email}/{dermEmail}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getDermatologistRating(@PathVariable String email, @PathVariable String dermEmail){

        return new ResponseEntity<>(dermatologistRatingService.getRating(email, dermEmail), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/dermatologist/rate/{email}/{dermEmail}/{rating}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> rateDermatologist(@PathVariable String email,
                                               @PathVariable String dermEmail, @PathVariable Double rating){

        dermatologistRatingService.addRating(email, dermEmail, rating);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "get/order/{date}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDate(@PathVariable String date){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime mytime = LocalDateTime.parse(date);
        mytime.plusHours(24);

            String retVal = "NotOk";
            if (mytime.isAfter(now)) {
                retVal = "ok";
            }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
