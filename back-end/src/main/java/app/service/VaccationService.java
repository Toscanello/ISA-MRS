package app.service;

import app.domain.Vaccation;
import app.repository.VaccationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccationService{
    @Autowired
    VaccationRepository vaccationRepository;

    public void save(Vaccation vaccation) {
        vaccationRepository.save(vaccation);
    }

    public List<Vaccation> findAll() {
        return vaccationRepository.findAll();
    }

    public List<Vaccation> findVacationsByMedicalWorkerEmail(String email) {
        return vaccationRepository.findVaccationsByMedicalWorkerEmail(email);
    }

    public Vaccation findVacationByMedicalWorkerEmail(String email) {
        return vaccationRepository.findVaccationByMedicalWorkerEmail(email);
    }
}
