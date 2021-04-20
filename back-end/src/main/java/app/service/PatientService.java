package app.service;

import app.domain.Patient;
import app.domain.Pharmacy;

import java.util.List;
import java.util.Map;

public interface PatientService {
    List<Patient> getAll();
    List<Patient> searchPatients(Map<String,String> search);
    Pharmacy getPharmacy(String regNo);
    List<Patient> findAllPatientsByPharmacistEmail(String email);
    Patient findOneByEmail(String email);
}
