package app.service;

import app.domain.*;
import app.dto.FreeAppointmentDTO;
import app.repository.DermatologistAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DermatologistAppointmentService {
    @Autowired
    DermatologistAppointmentRepository dermatologistAppointmentRepository;

    private DermatologistWorkHourService workHourService;
    private AppointmentService appointmentService;
    private PharmacyService pharmacyService;
    private DermatologistService dermatologistService;
    private UserService userService;

    public void setDermatologistAppointmentRepository(DermatologistAppointmentRepository dermatologistAppointmentRepository) {
        this.dermatologistAppointmentRepository = dermatologistAppointmentRepository;
    }

    public void setWorkHourService(DermatologistWorkHourService workHourService) {
        this.workHourService = workHourService;
    }

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void setPharmacyService(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    public void setDermatologistService(DermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    public DermatologistAppointment save(DermatologistAppointment da) {
        return dermatologistAppointmentRepository.save(da);
    }

    public List<DermatologistAppointment> findFreeAppointmentsByDermatologist(String email) {
        return dermatologistAppointmentRepository.findFreeAppointmentsByDermatologistEmail(email);
    }

    public List<DermatologistAppointment> findActiveAppointmentsByDermatologist(String email, String pharmacy){
        return dermatologistAppointmentRepository.findFreeAppointmentsByDermatologistEmailAndPharmacy(email,pharmacy);
    }

    public List<DermatologistAppointment> findFreeAppointmentsByPharmacyRegNo(String regNo){
        return dermatologistAppointmentRepository.findFreeAppointmentsByPharmacyRegNo(regNo, LocalDateTime.now());
    }

    public void deleteDermatologistAppointment(Long id){
        dermatologistAppointmentRepository.deleteDermatologistAppointment(id);
    }

    public void saveNewAppointment(Appointment appointment, Dermatologist dermatologist){
        if(appointment.getMedicalWorker().getRoles().get(0).getName().equals("ROLE_DERMATOLOGIST"))
        {
            DermatologistAppointment dt = new DermatologistAppointment();
            dt.setPharmacy(appointment.getPharmacy());
            dt.setPrice(appointment.getPrice());
            dt.setTime(appointment.getStartTime());
            dt.setDuration(appointment.getEndTime().toLocalTime().minusMinutes(appointment.getStartTime().toLocalTime().getMinute()));
            dt.setDermatologist(dermatologist);
            dermatologistAppointmentRepository.save(dt);
        }
    }

    public DermatologistAppointment findById(Long id){
        return dermatologistAppointmentRepository.findOneById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<String> addNewFreeAppointment(FreeAppointmentDTO newAppointment) {
        User u = userService.getAndIncrement(newAppointment.getDermatologistEmail());
        Dermatologist d = dermatologistService.findDermatologist(newAppointment.getDermatologistEmail());

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

        List<DermatologistAppointment> freeDermatologistAppointments
                = dermatologistAppointmentRepository.findFreeAppointmentsByDermatologistEmail(newAppointment.getDermatologistEmail());
        for (DermatologistAppointment da : freeDermatologistAppointments) {
            if (da.getTime().isBefore(appointmentEndLDT) && da.getEndTime().isAfter(appointmentBeginLDT)) {
                return new ResponseEntity<>("Dermatologist already has a scheduled appointment at that time", HttpStatus.OK);
            }
        }

        Pharmacy p = pharmacyService.getPharmacy(newAppointment.getPharmacyRegNo());
        DermatologistAppointment da = new DermatologistAppointment(
                appointmentBeginLDT,
                appointmentDuration,
                d,
                p,
                newAppointment.getPrice()
        );

        dermatologistAppointmentRepository.save(da);
        userService.saveUser(u);
        return new ResponseEntity<>("Successfully added a new appointment", HttpStatus.OK);
    }
}
