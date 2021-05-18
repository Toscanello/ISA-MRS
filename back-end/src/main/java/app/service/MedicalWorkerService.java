package app.service;

import app.domain.MedicalWorker;
import app.repository.MedicalWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalWorkerService {
    @Autowired
    private MedicalWorkerRepository medicalWorkerRepository;

    public MedicalWorker findOneByEmail(String email){
        return medicalWorkerRepository.findOneByEmail(email);
    }
}
