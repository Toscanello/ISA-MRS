package app.service;

import app.domain.Appointment;
import app.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> findAllByMedicalWorkerEmail(String email) {
        return appointmentRepository.findAllByMedicalWorkerEmail(email);
    }

    public Appointment save(Appointment ap) {
        return appointmentRepository.save(ap);
    }
}
