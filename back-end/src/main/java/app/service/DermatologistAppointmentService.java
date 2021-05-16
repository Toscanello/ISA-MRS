package app.service;

import app.domain.DermatologistAppointment;
import app.repository.DermatologistAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistAppointmentService {
    @Autowired
    DermatologistAppointmentRepository dermatologistAppointmentRepository;

    public DermatologistAppointment save(DermatologistAppointment da) {
        return dermatologistAppointmentRepository.save(da);
    }

    public List<DermatologistAppointment> findActiveAppointmentsByDermatologist(String email, String pharmacy){
        return dermatologistAppointmentRepository.findFreeAppointmentsByDermatologistEmailAndPharmacy(email,pharmacy);
    }

    public List<DermatologistAppointment> findFreeAppointmentsByPharmacyRegNo(String regNo){
        return dermatologistAppointmentRepository.findFreeAppointmentsByPharmacyRegNo(regNo);
    }

    public void deleteDermatologistAppointment(Long id){
        dermatologistAppointmentRepository.deleteDermatologistAppointment(id);
    }

}
