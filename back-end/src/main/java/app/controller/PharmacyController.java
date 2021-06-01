package app.controller;

import app.domain.*;
import app.dto.DermatologistAppointmentDTO;
import app.dto.PharmacistDTO;
import app.dto.SimpleDermatologistDTO;
import app.dto.SimplePharmacyDTO;
import app.repository.MedicalWorkerRepository;
import app.repository.MedicineOrderRepository;
import app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    MedicalWorkerRepository medicalWorkerRepository;

    @Autowired
    MedicineOrderRepository medicineOrderRepository;

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

    @GetMapping(value = "/pharmacy/appointments/{regNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DermatologistAppointmentDTO>>
    getPharmacyAppointmentsByRegNo(@PathVariable String regNo) {
        List<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.findFreeAppointmentsByPharmacyRegNo(regNo);
        List<DermatologistAppointmentDTO> dermatologistAppointmentDTOs = new ArrayList<>();

        for (DermatologistAppointment da : dermatologistAppointments) {
            dermatologistAppointmentDTOs.add(new DermatologistAppointmentDTO(da)); }

        return new ResponseEntity<>(dermatologistAppointmentDTOs, HttpStatus.OK);
    }

    @PostMapping(value = "/new/appointment/{email}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> reserveAppointment(@PathVariable String email, @RequestBody DermatologistAppointment dermatologistAppointment){

        Appointment a = appointmentService.saveAppointment(dermatologistAppointment, patientService.findOneByEmail(email),
                                            medicalWorkerRepository.findOneByEmail(dermatologistAppointment.getDermatologist().getEmail()),
                                            pharmacyService.getPharmacy(dermatologistAppointment.getPharmacy().getRegNo()));

        dermatologistAppointmentService.deleteDermatologistAppointment(dermatologistAppointment.getId());
        appointmentService.sendEmail(a);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping(value = "admin/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimplePharmacyDTO>
    getPharmacyByAdmin(@PathVariable String email) {
        Pharmacy p = pharmacyService.getPharmacyByAdmin(email);
        return new ResponseEntity<>(new SimplePharmacyDTO(p), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/{email}")
    public ResponseEntity<List<SimplePharmacyDTO>>
    getPharmaciesForDermatologist(@PathVariable String email){
        List<Pharmacy> pharmacies=pharmacyService.getPharmaciesByDermatologist(email);
        List<SimplePharmacyDTO> simplePharmacyDTOS = new ArrayList<>();
        for(Pharmacy p: pharmacies){
            simplePharmacyDTOS.add(new SimplePharmacyDTO(p));
        }
        return new ResponseEntity<>(simplePharmacyDTOS,HttpStatus.OK);
    }

    @GetMapping(value = "/report/appointments/{regNo}/{interval}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Integer>>
    getAppointmentReport(@PathVariable String regNo, @PathVariable String interval) {
        List<Appointment> allAppointments = appointmentService.findActiveAppointmentsByPharmacy(regNo);
        HashMap<String, Integer> appointmentsMap = new HashMap<>();
        for (Appointment ap : allAppointments) {
            if (interval.equals("quartal")) {
                appointmentsMap.putIfAbsent("1st Quartal", 0);
                appointmentsMap.putIfAbsent("2nd Quartal", 0);
                appointmentsMap.putIfAbsent("3rd Quartal", 0);
                appointmentsMap.putIfAbsent("4th Quartal", 0);

                int month = ap.getStartTime().getMonth().getValue();
                if (month <= 3) {
                    if (appointmentsMap.containsKey("1st Quartal")) {
                        int quartalCount = appointmentsMap.get("1st Quartal");
                        appointmentsMap.put("1st Quartal", ++quartalCount);
                    }
                    else
                        appointmentsMap.put("1st Quartal", 1);
                }
                else if (month <= 6) {
                    if (appointmentsMap.containsKey("2nd Quartal")) {
                        int quartalCount = appointmentsMap.get("2nd Quartal");
                        appointmentsMap.put("2nd Quartal", ++quartalCount);
                    }
                    else
                        appointmentsMap.put("2nd Quartal", 1);
                }
                else if (month <= 9) {
                    if (appointmentsMap.containsKey("3rd Quartal")) {
                        int quartalCount = appointmentsMap.get("3rd Quartal");
                        appointmentsMap.put("3rd Quartal", ++quartalCount);
                    }
                    else
                        appointmentsMap.put("3rd Quartal", 1);
                }
                else {
                    if (appointmentsMap.containsKey("4th Quartal")) {
                        int quartalCount = appointmentsMap.get("4th Quartal");
                        appointmentsMap.put("4th Quartal", ++quartalCount);
                    }
                    else
                        appointmentsMap.put("4th Quartal", 1);
                }
            }
            else if (interval.equals("annual")) {
                for (Month monthVal : Month.values()) {
                    appointmentsMap.putIfAbsent(stringifyMonth(monthVal), 0);
                }
                String month = stringifyMonth(ap.getStartTime().getMonth());
                if (appointmentsMap.containsKey(month)) {
                    int monthCount = appointmentsMap.get(month);
                    appointmentsMap.put(month, ++monthCount);
                }
                else
                    appointmentsMap.put(month, 1);
            }
            else if (interval.equals("monthly")) {
                if (ap.getEndTime().getMonth().getValue() != LocalDate.now().getMonth().getValue())
                    continue;
                for (int i = 1; i < 6; i++) {
                    appointmentsMap.putIfAbsent(Integer.toString(i), 0);
                }
                int week_no = ap.getEndTime().getDayOfMonth() / 7 + 1;
                String week = Integer.toString(week_no);
                if (appointmentsMap.containsKey(week)) {
                    int monthCount = appointmentsMap.get(week);
                    appointmentsMap.put(week, ++monthCount);
                }
                else
                    appointmentsMap.put(week, 1);
            }
        }
        return new ResponseEntity<>(appointmentsMap, HttpStatus.OK);
    }

    @GetMapping(value = "/report/income/{regNo}/{interval}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Double>>
    getIncomeReport(@PathVariable String regNo, @PathVariable String interval) {
        List<MedicineOrder> allOrders = medicineOrderRepository.findAllByPharmacyRegNo(regNo);
        List<Appointment> allAppointments = appointmentService.findActiveAppointmentsByPharmacy(regNo);
        HashMap<String, Double> incomeMap = new HashMap<>();

        for (MedicineOrder order : allOrders) {
            if (interval.equals("quartal")) {
                int month = order.getEndTime().getMonth().getValue();

                incomeMap.putIfAbsent("1st Quartal", 0.);
                incomeMap.putIfAbsent("2nd Quartal", 0.);
                incomeMap.putIfAbsent("3rd Quartal", 0.);
                incomeMap.putIfAbsent("4th Quartal", 0.);

                if (month <= 3) {
                    Double totalPrice = incomeMap.getOrDefault("1st Quartal", 0.);
                    totalPrice += order.getPrice();
                    incomeMap.put("1st Quartal", totalPrice);
                }
                else if (month <= 6) {
                    Double totalPrice = incomeMap.getOrDefault("2nd Quartal", 0.);
                    totalPrice += order.getPrice();
                    incomeMap.put("2nd Quartal", totalPrice);
                }
                else if (month <= 9) {
                    Double totalPrice = incomeMap.getOrDefault("3rd Quartal", 0.);
                    totalPrice += order.getPrice();
                    incomeMap.put("3rd Quartal", totalPrice);
                }
                else {
                    Double totalPrice = incomeMap.getOrDefault("4th Quartal", 0.);
                    totalPrice += order.getPrice();
                    incomeMap.put("4th Quartal", totalPrice);
                }
            }
            else if (interval.equals("annual")) {
                for (Month monthVal : Month.values()) {
                    incomeMap.putIfAbsent(stringifyMonth(monthVal), 0.);
                }
                String month = stringifyMonth(order.getEndTime().getMonth());
                Double totalPrice = incomeMap.getOrDefault(month, 0.);
                totalPrice += order.getPrice();
                incomeMap.put(month, totalPrice);
            }
            else if (interval.equals("monthly")) {
                if (order.getEndTime().getMonth().getValue() != LocalDate.now().getMonth().getValue())
                    continue;
                for (int i = 1; i < 6; i++) {
                    incomeMap.putIfAbsent(Integer.toString(i), 0.);
                }
                int weekNo = order.getEndTime().getDayOfMonth() / 7 + 1;
                String week = Integer.toString(weekNo);
                Double totalPrice = incomeMap.getOrDefault(week, 0.);
                totalPrice += order.getPrice();
                incomeMap.put(week, totalPrice);
            }
        }

        for (Appointment appointment : allAppointments) {
            if (interval.equals("quartal")) {
                incomeMap.putIfAbsent("1st Quartal", 0.);
                incomeMap.putIfAbsent("2nd Quartal", 0.);
                incomeMap.putIfAbsent("3rd Quartal", 0.);
                incomeMap.putIfAbsent("4th Quartal", 0.);

                int month = appointment.getEndTime().getMonth().getValue();
                if (month <= 3) {
                    Double totalPrice = incomeMap.getOrDefault("1st Quartal", 0.);
                    totalPrice += appointment.getPrice();
                    incomeMap.put("1st Quartal", totalPrice);
                } else if (month <= 6) {
                    Double totalPrice = incomeMap.getOrDefault("2nd Quartal", 0.);
                    totalPrice += appointment.getPrice();
                    incomeMap.put("2nd Quartal", totalPrice);
                } else if (month <= 9) {
                    Double totalPrice = incomeMap.getOrDefault("3rd Quartal", 0.);
                    totalPrice += appointment.getPrice();
                    incomeMap.put("3rd Quartal", totalPrice);
                } else {
                    Double totalPrice = incomeMap.getOrDefault("4th Quartal", 0.);
                    totalPrice += appointment.getPrice();
                    incomeMap.put("4th Quartal", totalPrice);
                }
            }
            else if (interval.equals("annual")) {
                for (Month monthVal : Month.values()) {
                    incomeMap.putIfAbsent(stringifyMonth(monthVal), 0.);
                }
                String month = stringifyMonth(appointment.getEndTime().getMonth());
                Double totalPrice = incomeMap.getOrDefault(month, 0.);
                totalPrice += appointment.getPrice();
                incomeMap.put(month, totalPrice);
            }
            else if (interval.equals("monthly")) {
                if (appointment.getEndTime().getMonth().getValue() != LocalDate.now().getMonth().getValue())
                    continue;
                for (int i = 1; i < 6; i++) {
                    incomeMap.putIfAbsent(Integer.toString(i), 0.);
                }
                int weekNo = appointment.getEndTime().getDayOfMonth() / 7 + 1;
                String week = Integer.toString(weekNo);
                Double totalPrice = incomeMap.getOrDefault(week, 0.);
                totalPrice += appointment.getPrice();
                incomeMap.put(week, totalPrice);
            }
        }
        return new ResponseEntity<>(incomeMap, HttpStatus.OK);
    }

    @GetMapping(value = "/report/medicine/{regNo}/{interval}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Integer>>
    getMedicineReport(@PathVariable String regNo, @PathVariable String interval) {
        List<MedicineOrder> allOrders = medicineOrderRepository.findAllByPharmacyRegNo(regNo);
        HashMap<String, Integer> ordersMap = new HashMap<>();
        for (MedicineOrder order : allOrders) {
            if (interval.equals("quartal")) {
                ordersMap.putIfAbsent("1st Quartal", 0);
                ordersMap.putIfAbsent("2nd Quartal", 0);
                ordersMap.putIfAbsent("3rd Quartal", 0);
                ordersMap.putIfAbsent("4th Quartal", 0);

                int month = order.getEndTime().getMonth().getValue();
                if (month <= 3) {
                    int quartalCount = ordersMap.getOrDefault("1st Quartal", 0);
                    ordersMap.put("1st Quartal", ++quartalCount);
                }
                else if (month <= 6) {
                    int quartalCount = ordersMap.getOrDefault("2nd Quartal", 0);
                    ordersMap.put("2nd Quartal", ++quartalCount);
                }
                else if (month <= 9) {
                    int quartalCount = ordersMap.getOrDefault("3rd Quartal", 0);
                    ordersMap.put("3rd Quartal", ++quartalCount);
                }
                else {
                    int quartalCount = ordersMap.getOrDefault("4th Quartal", 0);
                    ordersMap.put("4th Quartal", ++quartalCount);
                }
            }
            else if (interval.equals("annual")) {
                for (Month monthVal : Month.values()) {
                    ordersMap.putIfAbsent(stringifyMonth(monthVal), 0);
                }
                String month = stringifyMonth(order.getEndTime().getMonth());
                int monthlyCount = ordersMap.getOrDefault(month, 0);
                ordersMap.put(month, ++monthlyCount);
            }
            else if (interval.equals("monthly")) {
                if (order.getEndTime().getMonth().getValue() != LocalDate.now().getMonth().getValue())
                    continue;
                for (int i = 1; i < 6; i++) {
                    ordersMap.putIfAbsent(Integer.toString(i), 0);
                }
                int weekNo = order.getEndTime().getDayOfMonth() / 7 + 1;
                String week = Integer.toString(weekNo);
                int weeklyCount = ordersMap.getOrDefault(week, 0);
                ordersMap.put(week, ++weeklyCount);
            }
        }

        return new ResponseEntity<>(ordersMap, HttpStatus.OK);
    }

    public String stringifyMonth(Month month) {
        int monthOrdinal = month.getValue();
        String monthString = String.format("%d. %s", monthOrdinal, month.toString());
        return monthOrdinal >= 10 ? monthString : "0" + monthString;
    }

    @GetMapping(value = "/search/{searchParam}")
    public ResponseEntity<List<SimplePharmacyDTO>> searchPharmacies(@PathVariable String searchParam) {
        List<Pharmacy> pharmacies = pharmacyService.searchPharmacies(searchParam);
        List<SimplePharmacyDTO> pharmaciesDTO = new ArrayList<>();

        for (Pharmacy p : pharmacies)
        {
            pharmaciesDTO.add(new SimplePharmacyDTO(p));
        }
        return new ResponseEntity<>(pharmaciesDTO, HttpStatus.OK);
    }
}
