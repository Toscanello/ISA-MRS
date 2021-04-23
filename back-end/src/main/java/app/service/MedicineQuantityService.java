package app.service;

import app.domain.MedicineQuantity;
import app.repository.MedicineQuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineQuantityService {
    @Autowired
    MedicineQuantityRepository medicineQuantityRepository;

    public List<MedicineQuantity> findMedicineQuantitiesByPharmacyRegNo(String regNo) {
        return medicineQuantityRepository.findMedicineQuantitiesByPharmacyRegNo(regNo);
    }

    public void deleteMedicineQuantityByPharmacy(String regNo, String code) {
        medicineQuantityRepository.deleteMedicineQuantityByPharmacy(regNo, code);
    }
}
