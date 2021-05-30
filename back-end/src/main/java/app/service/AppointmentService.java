package app.service;

import app.domain.*;
import app.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    private JavaMailSender emailSender;

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

    public Appointment saveAppointment(DermatologistAppointment dermatologistAppointment, Patient patient, MedicalWorker medicalWorker, Pharmacy pharmacy) {
        Appointment a = new Appointment();
        a.setCanceled(false);
        a.setPrice(dermatologistAppointment.getPrice());
        a.setStartTime(dermatologistAppointment.getTime());
        LocalDateTime endTime = dermatologistAppointment.getTime().plusMinutes(dermatologistAppointment.getDuration().getMinute());
        a.setEndTime(endTime);
        a.setPatient(patient);
        a.setMedicalWorker(medicalWorker);
        a.setPharmacy(pharmacy);

        return appointmentRepository.save(a);
    }

    public void sendEmail(Appointment a){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("servis.apoteka@gmail.com");
        message.setTo("klimentaj99@gmail.com");
        message.setSubject("Rezervacija pregleda");
        message.setText("Rezervisan pregled " + a.getStartTime().getDayOfMonth() + "." +  a.getStartTime().getMonth() +
                " sa pocetkom u " + a.getStartTime().getHour() + "h i" + a.getStartTime().getMinute() + "min" );
        emailSender.send(message);
    }

    public Appointment findOneById(Long id){
        return appointmentRepository.findOneById(id);
    }

    public List<Appointment> findPatientsAppointments(String email){
        return appointmentRepository.findPatientsAppointments(email);
    }

    public boolean createNewAppointment(DermatologistAppointment da, Patient patient) {
        LocalDateTime startTime = da.getTime();
        LocalDateTime endTime = startTime.plusMinutes(da.getDuration().getMinute());

        Dermatologist d = da.getDermatologist();
        Pharmacy ph = da.getPharmacy();
        Double price = da.getPrice();
        System.out.println(patient.getEmail());
        List<Appointment> appointmentList = appointmentRepository.findActiveAppointmentsByPatientId(patient.getEmail());
        boolean check = false;
        if(appointmentList != null) {
            for (Appointment a : appointmentList) {
                if ((startTime.isAfter(a.getStartTime()) && startTime.isBefore(a.getEndTime())) || (endTime.isAfter(a.getStartTime()) && endTime.isBefore(a.getEndTime()))) {
                    check = true;
                    break;
                }
            }
        }
        Appointment a=null;
        if(!check) {
            a = new Appointment(startTime, endTime, patient, d, ph, price, false);
            appointmentRepository.save(a);
            return true;
        }
        return false;
    }

    public List<Appointment> findActiveAppointmentsByPharmacy(String regNo) {
        return appointmentRepository.findActiveAppointmentsByPharmacy(regNo);
    }
}
