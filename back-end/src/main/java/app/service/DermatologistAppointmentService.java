package app.service;

import app.domain.DermatologistAppointment;
import app.repository.DermatologistAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public DermatologistAppointment findById(Long id){
        return dermatologistAppointmentRepository.findOneById(id);
    }

    public void delete(DermatologistAppointment da) {
        dermatologistAppointmentRepository.delete(da);
    }
}
