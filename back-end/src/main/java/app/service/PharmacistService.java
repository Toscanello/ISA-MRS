package app.service;

import app.domain.Pharmacist;
import app.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacistService {
    @Autowired
    PharmacistRepository pharmacistRepository;

    public Pharmacist findPharmacistByEmail(String email){
        return pharmacistRepository.findOneByEmail(email);
    }
}
