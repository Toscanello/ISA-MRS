package app.service;

import app.domain.*;
import app.dto.MedicalWorkerDTO;
import app.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<Dermatologist> findDermatologistForRating(String patientEmail){
        Set<String> dermatologistEmails = dermatologistRepository.findDermatologistForRating(patientEmail);
        Set<Dermatologist> dermatologists = new HashSet<>();

        for (String email : dermatologistEmails)
            dermatologists.add(dermatologistRepository.findOneByEmail(email));
        return dermatologists;
    }
}
