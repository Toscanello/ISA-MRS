package app.service;

import app.domain.Address;
import app.domain.Location;
import app.domain.Patient;
import app.domain.Pharmacy;
import app.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final List<Patient> patients = new ArrayList<Patient>() {
        {
            add(new Patient("pera@gmail.com", "pass", "pera", "peric", "+38162514578",
                    new Address("ulica", "broj", "grad", "Srbija",
                            new Location(24.5, 24.5))));
            add(new Patient("mika@gmail.com", "pass", "mika", "mikic", "+38162514578",
                    new Address("ulica", "broj", "grad", "Srbija",
                            new Location(24.5, 24.5))));
            add(new Patient("zika@gmail.com", "pass", "zika", "zikic", "+38162514578",
                    new Address("ulica", "broj", "grad", "Srbija",
                            new Location(24.5, 24.5))));
        }
    };

    private final Collection<Pharmacy> pharmacies = new ArrayList<>();

    @Autowired
    private PatientRepository patientRepository;

    public PatientServiceImpl() {
        Location loc = new Location(19.8425, 43.8556);
        Address addr = new Address("Omladinska", "2a", "Uzice", "Serbia", loc);
        Pharmacy p = new Pharmacy("abc", "Krsenkovic", addr);
        pharmacies.add(p);
    }

    @Override
    public List<Patient> getAll() {
        return this.patients;
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
    public List<Patient> findAllStudentsByPharmacistEmail(String email) {
        return patientRepository.findAllStudentsByPharmacistEmail(email);
    }
}
