package app.service;

import app.domain.PharmacyAdmin;
import app.repository.PharmacyAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyAdminService {
    @Autowired
    private PharmacyAdminRepository pharmacyAdminRepository;

    public List<PharmacyAdmin> findaAllByPharamcy(String regNo){
        return pharmacyAdminRepository.findAllByPharmacyRegNo(regNo);
    }
}
