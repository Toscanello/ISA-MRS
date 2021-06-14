package app.service;

import app.domain.Medicine;
import app.domain.MedicineOrder;
import app.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public List<Medicine> findPatientsAllergies(String patient_email){
        return medicineRepository.findPatientsAllergies(patient_email);
    }

    public Set<Medicine> findMedicineForRating(String email){
        return medicineRepository.findMedicineForRating(email);
    }
}
