package app.service;

import app.domain.Appointment;
import app.domain.Dermatologist;
import app.domain.DermatologistAppointment;
import app.repository.DermatologistAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DermatologistAppointmentService {
    @Autowired
    DermatologistAppointmentRepository dermatologistAppointmentRepository;

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
}
