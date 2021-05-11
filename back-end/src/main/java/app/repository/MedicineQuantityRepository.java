package app.repository;

import app.domain.MedicineQuantity;
import app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicineQuantityRepository extends JpaRepository<MedicineQuantity, Long> {
    @Query(
            value = "select mq from MedicineQuantity mq where mq.pharmacy.regNo = ?1"
    )
    public List<MedicineQuantity> findMedicineQuantitiesByPharmacyRegNo(String regNo);

    @Query(
            value = "select mq from MedicineQuantity mq where mq.pharmacy.regNo = ?1 and mq.medicine.code = ?2"
    )
    public MedicineQuantity findMedicineQuantityByPharmacy(String regNo, String code);

    @Transactional
    @Modifying
    @Query(
            value = "delete from medicine_quantity mq where mq.pharmacy_reg_no = ?1 and mq.medicine_code = ?2",
            nativeQuery = true
    )
    public void deleteMedicineQuantityByPharmacy(String regNo, String code);

    MedicineQuantity findById(long id);

    @Query(
            value = "select mq.quantity from medicine_quantity mq where mq.pharmacy_reg_no = ?1 and mq.medicine_code = ?2",
            nativeQuery = true
    )
    public int findMedicineQuantityByPharmacyRegNoAndMedicineCode(String regNo, String code);

    @Transactional
    @Modifying
    @Query(
            value = "update medicine_quantity set quantity = ?3 where pharmacy_reg_no = ?1 and medicine_code = ?2",
            nativeQuery = true
    )
    public void updateMedicineQuantityByPharmacyRegNoAndMedicineCode(String regNo, String code, int newQuantity);
}
