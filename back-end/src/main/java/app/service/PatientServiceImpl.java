package app.service;

import app.domain.Address;
import app.domain.Location;
import app.domain.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Patient> getAll() {
        return this.patients;
    }
}
