package app.service;

import app.domain.MedicineOrder;
import app.domain.MedicineQuantity;
import app.repository.MedicineQuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MedicineQuantityService {
    @Autowired
    MedicineQuantityRepository medicineQuantityRepository;

    public List<MedicineQuantity> findMedicineQuantitiesByPharmacyRegNo(String regNo) {
        return medicineQuantityRepository.findMedicineQuantitiesByPharmacyRegNo(regNo);
    }

    public MedicineQuantity findMedicineQuantityByPharmacyRegNo(String regNo, String code) {
        return medicineQuantityRepository.findMedicineQuantityByPharmacy(regNo, code);
    }

    public void deleteMedicineQuantityByPharmacy(String regNo, String code) {
        medicineQuantityRepository.deleteMedicineQuantityByPharmacy(regNo, code);
    }

    public MedicineQuantity findById(long id){
        return medicineQuantityRepository.findById(id);
    }

    @Transactional
    public int findMedicineQuantityByPharmacyRegNoAndMedicineCode(String regNo, String code){
        return medicineQuantityRepository.findMedicineQuantityByPharmacyRegNoAndMedicineCode(regNo, code);
    }

    public void updateMedicineQuantityByPharmacyRegNoAndMedicineCode(String regNo, String code, int newQuantity){
        medicineQuantityRepository.updateMedicineQuantityByPharmacyRegNoAndMedicineCode(regNo, code, newQuantity);
    }

    public MedicineQuantity save(MedicineQuantity mq) {
        return medicineQuantityRepository.save(mq);
    }

    @Transactional
    public void medicineOrderCancellation(MedicineOrder mo, String pharmacyRegNo, String medicineCode){
        MedicineQuantity mq = medicineQuantityRepository.findMedicineQuantityByPharmacy(pharmacyRegNo, medicineCode);
        int quantity = mq.getQuantity() + mo.getQuantity();
        medicineQuantityRepository.updateMedicineQuantityByPharmacyRegNoAndMedicineCode(pharmacyRegNo, medicineCode, quantity);

    }
}
