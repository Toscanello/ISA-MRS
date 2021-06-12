package app.service;

import app.domain.Appointment;
import app.domain.MedicineOrder;
import app.domain.Patient;
import app.domain.Pharmacy;
import app.dto.PatientDTO;

import java.util.List;
import java.util.Map;

public interface PatientService {
    List<Patient> searchPatients(Map<String,String> search);
    Pharmacy getPharmacy(String regNo);
    List<Patient> findAllPatientsByMedicalEmail(String email);
    Patient findOneByEmail(String email);
    Patient save(Patient patient, PatientDTO editedPatient);
    List<Patient> getAllSubscribedToPharmacy(String regNo);
    void addPenalty(Patient patient);
}
