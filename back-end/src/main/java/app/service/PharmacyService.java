package app.service;

import app.domain.DPharmacy;
import app.domain.Pharmacy;
import app.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy getPharmacy(String regNo) { return pharmacyRepository.findPharmacyByRegNo(regNo); }
}
