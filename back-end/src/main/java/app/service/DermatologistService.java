package app.service;

import app.domain.Appointment;
import app.domain.Dermatologist;
import app.domain.DermatologistAppointment;
import app.domain.DermatologistWorkHour;
import app.dto.MedicalWorkerDTO;
import app.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistService {
    @Autowired
    DermatologistRepository dermatologistRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Dermatologist> findAll() {
        return dermatologistRepository.findAll();
    }

    public List<Dermatologist> findPharmacyDermatologists(String regNo) {
        return dermatologistRepository.findAllDermatologistsByPharmacyRegNo(regNo);
    }

    public List<DermatologistWorkHour> getDermatologistWorkHours(String email) {
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

    public void edit(Dermatologist dermatologist, MedicalWorkerDTO medicalWorkerDTO) {
        dermatologist.setName(medicalWorkerDTO.getName());
        dermatologist.setSurname(medicalWorkerDTO.getSurname());
        dermatologist.setAddress(medicalWorkerDTO.getAddress());
        dermatologist.setPhoneNumber(medicalWorkerDTO.getPhoneNumber());
        if(!dermatologist.getPassword().equals(medicalWorkerDTO.getPassword()))
            dermatologist.setPassword(passwordEncoder.encode(medicalWorkerDTO.getPassword()));
        dermatologistRepository.save(dermatologist);
    }

    public Dermatologist save(Dermatologist dermatologist) {
        return dermatologistRepository.save(dermatologist);
    }

    public void addEmployement(String email, String regNo) {
        dermatologistRepository.addEmployement(email, regNo);
    }
}
