package app.service;

import app.domain.Pharmacist;
import app.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PharmacistService {
    @Autowired
    PharmacistRepository pharmacistRepository;

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
}
