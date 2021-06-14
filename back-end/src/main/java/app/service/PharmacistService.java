package app.service;

import app.domain.Patient;
import app.domain.Pharmacist;
import app.dto.MedicalWorkerDTO;
import app.dto.PatientDTO;
import app.dto.PharmacistDTO;
import app.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PharmacistService {
    @Autowired
    PharmacistRepository pharmacistRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Pharmacist findPharmacistByEmail(String email){
        return pharmacistRepository.findOneByEmail(email);
    }

    public List<String> findPharmacist(LocalTime start, LocalTime end, LocalDateTime startDate, String pharmacyRegNo){
        return pharmacistRepository.findPharmacist( start, end, startDate, pharmacyRegNo);
    }

    public List<Pharmacist> findPharmacistsByPharmacy(String regNo) {
        return pharmacistRepository.findPharmacistsByPharmacyRegNo(regNo);
    }

    public List<Pharmacist> findAll() {
        return pharmacistRepository.findAll();
    }

    public void save(Pharmacist pharmacist) {
        pharmacistRepository.save(pharmacist);
    }

    public void edit(Pharmacist pharmacist, MedicalWorkerDTO medicalWorkerDTO)
    {
        pharmacist.setName(medicalWorkerDTO.getName());
        pharmacist.setSurname(medicalWorkerDTO.getSurname());
        pharmacist.setAddress(medicalWorkerDTO.getAddress());
        pharmacist.setPhoneNumber(medicalWorkerDTO.getPhoneNumber());
        if(!pharmacist.getPassword().equals(medicalWorkerDTO.getPassword()))
            pharmacist.setPassword(passwordEncoder.encode(medicalWorkerDTO.getPassword()));
        pharmacistRepository.save(pharmacist);
    }

    public List<Pharmacist> getUnemployed() { return pharmacistRepository.getUnemployed(); }

    public Set<Pharmacist> findPharmacistForRating(String patientEmail){
        Set<String> pharmacistEmails = pharmacistRepository.findPharmacistForRating(patientEmail);
        Set<Pharmacist> pharmacists = new HashSet<>();

        for (String email : pharmacistEmails)
            pharmacists.add(pharmacistRepository.findOneByEmail(email));
        return pharmacists;
    }
}
