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

    public List<Appointment> getAllAppointments(String email) {
        return appointmentRepository.findAllByMedicalWorkerEmail(email);
    }
    public List<Appointment> getAllAppointmentsByPatientId(String email) {
        return appointmentRepository.findAllByPatientId(email);
    }

    public List<Appointment> findActiveAppointmentsByPatientId(String email){
        return appointmentRepository.findActiveAppointmentsByPatientId(email);
    }

    public List<Appointment> findActiveAppointmentsByPharmacistId(String email){
        return appointmentRepository.findActiveAppointmentsByPharmacistId(email);
    }

    public Appointment save(Appointment ap) {
        return appointmentRepository.save(ap);
    }

    public void cancelAppointment(Long id){appointmentRepository.cancelAppointment(id); }

    public List<Appointment> findActiveAppointmentsByDermatologist(String email, String pharmacy) {
        return appointmentRepository.findActiveAppointmentsByDermatologist(email, pharmacy);
    }
}
