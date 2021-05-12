package app.service;

import app.domain.DPharmacy;
import app.domain.Pharmacist;
import app.domain.Pharmacy;
import app.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy getPharmacy(String regNo) { return pharmacyRepository.findPharmacyByRegNo(regNo); }

    public List<Pharmacy> getPharmaciesByTime(LocalTime start, LocalTime end, LocalDateTime localDate) {
        return pharmacyRepository.findPharmacyPharmacistWorkHour(start, end, localDate);
    }

    public Pharmacy getPharmacyByAdmin(String email) {
        return pharmacyRepository.findPharmacyByAdmin(email);
    }

    public Pharmacy save(Pharmacy p) { return pharmacyRepository.save(p); }

    public List<Pharmacy> getPharmaciesByDermatologist(String email) {
        return pharmacyRepository.findPharmacyByDermatologist(email);
    }
}
