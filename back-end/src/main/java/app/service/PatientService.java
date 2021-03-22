package app.service;

import app.domain.Patient;
import app.domain.Pharmacy;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Pharmacy getPharmacy(String regNo);
}
