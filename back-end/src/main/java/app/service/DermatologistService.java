package app.service;

import app.domain.Appointment;
import app.domain.Dermatologist;
import app.domain.DermatologistAppointment;
import app.domain.WorkHour;
import app.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistService {
    @Autowired
    DermatologistRepository dermatologistRepository;

    public List<Dermatologist> findPharmacyDermatologists(String regNo) {
        return dermatologistRepository.findAllDermatologistsByPharmacyRegNo(regNo);
    }

    public List<WorkHour> getDermatologistWorkHours(String email) {
        return dermatologistRepository.findWorkHoursByDermatologistEmail(email);
    }

    public List<DermatologistAppointment> getFreeAppointments(String email) {
        return dermatologistRepository.findFreeAppointmetsByDermatologistEmail(email);
    }

    public List<Appointment> getAllAppointments(String email) {
        return dermatologistRepository.findAllAppointmentsByDermatologistEmail(email);
    }

    public Dermatologist findDermatologist(String email) {
        return dermatologistRepository.findDermatologistByEmail(email);
    }
}
