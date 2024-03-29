package app.service;

import app.domain.*;
import app.dto.PatientDTO;
import app.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final List<Patient> patients = new ArrayList<Patient>();

    private final Collection<Pharmacy> pharmacies = new ArrayList<>();

    @Autowired
    private PatientRepository patientRepository;

    public PatientServiceImpl() {
        Location loc = new Location(19.8425, 43.8556);
        Address addr = new Address("Omladinska", "2a", "Uzice", "Serbia", loc);
        Pharmacy p = new Pharmacy("abc", "Krsenkovic", addr, 1000.00);
        pharmacies.add(p);
    }

    @Override
    public List<Patient> getAll() {
        return this.patients;
    }

    @Override
    public List<Patient> searchPatients(Map<String, String> search){
        return patientRepository.searchPatients(search.get("email"),search.getOrDefault("name",""),search.getOrDefault("surname",""));
    }

    @Override
    public Pharmacy getPharmacy(String regNo) {
        for (Pharmacy p : pharmacies) {
            if (p.getRegNo().equals(regNo))
                return p;
        }
        return null;
    }

    @Override
    public List<Patient> findAllPatientsByMedicalEmail(String email) {
        return patientRepository.findAllStudentsByMedicalEmail(email);
    }

    @Override
    public Patient findOneByEmail(String email){
        return patientRepository.findOneByEmail(email);
    }

    @Override
    public Patient save(Patient patient, PatientDTO editedPatient)
    {
        patient.setName(editedPatient.getName());
        patient.setSurname(editedPatient.getSurname());
        patient.setAddress(editedPatient.getAddress());
        patient.setPhoneNumber(editedPatient.getPhoneNumber());
        if(!patient.getPassword().equals(editedPatient.getPassword()))
            patient.setPassword(passwordEncoder.encode(editedPatient.getPassword()));
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllSubscribedToPharmacy(String regNo) {
        return patientRepository.findAllSubscribedToPharmacy(regNo);
    }

    @Override
    public void insertAllergy(String patient_email, String medicine_code){
        if(patientRepository.findAllergy(patient_email, medicine_code) == null)
            patientRepository.insertAllergy(patient_email, medicine_code);
    }

    @Override
    public void deleteAllergy(String patient_email, String medicine_code){
        patientRepository.deleteAllergy(patient_email, medicine_code);
    }

    @Override
    public void erasePenalties() {
        patientRepository.erasePenalties();
    }

    @Override
    public void addPenalty(Patient patient) {
        patientRepository.addPenalty(patient.getEmail(),patient.getPenalty()+1);
    }



}
