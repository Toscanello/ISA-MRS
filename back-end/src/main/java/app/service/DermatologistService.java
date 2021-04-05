package app.service;

import app.domain.Dermatologist;
import app.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistService {
    @Autowired
    DermatologistRepository dermatologistRepository;

    public List<Dermatologist> findPharmacyDermatologists(String regNo) {
        return dermatologistRepository.findAllDermatologistsByPharmacyRegNo(regNo);
    }
}
