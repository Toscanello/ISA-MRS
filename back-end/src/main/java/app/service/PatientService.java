package app.service;

import app.domain.*;
import app.dto.PatientDTO;

import java.util.List;
import java.util.Map;

public interface PatientService {
    List<Patient> getAll();
    List<Patient> searchPatients(Map<String,String> search);
    Pharmacy getPharmacy(String regNo);
    List<Patient> findAllPatientsByMedicalEmail(String email);
    Patient findOneByEmail(String email);
    Patient save(Patient patient, PatientDTO editedPatient);
    List<Patient> getAllSubscribedToPharmacy(String regNo);
    void insertAllergy(String patient_email, String medicine_code);
    void deleteAllergy(String patient_email, String medicine_code);
}
