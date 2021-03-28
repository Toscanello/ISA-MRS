package app.service;

import app.domain.DPharmacy;
import app.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    public List<DPharmacy> findAll() {
        return pharmacyRepository.findAll();
    }
}
