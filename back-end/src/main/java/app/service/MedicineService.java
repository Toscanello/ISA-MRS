package app.service;

import app.domain.Medicine;
import app.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    MedicineRepository medicineRepository;

    public List<Medicine> getAllMedicines() {
        return this.medicineRepository.findAll();
    }

    public Medicine findMedicineByCode(String code) {
        return this.medicineRepository.getMedicineByCode(code);
    }

    public List<Medicine> findMedicineByPharmacy(String regNo) {
        return this.medicineRepository.findMedicineByPharmacyRegNo(regNo);
    }
}
