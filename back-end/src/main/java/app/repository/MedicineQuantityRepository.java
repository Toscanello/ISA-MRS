package app.repository;

import app.domain.MedicineQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicineQuantityRepository extends JpaRepository<MedicineQuantity, Long> {
    @Query(
            value = "select * from medicine_quantity mq where mq.pharmacy_reg_no = ?1",
            nativeQuery = true
    )
    public List<MedicineQuantity> findMedicineQuantitiesByPharmacyRegNo(String regNo);

    @Transactional
    @Modifying
    @Query(
            value = "delete from medicine_quantity mq where mq.pharmacy_reg_no = ?1 and mq.medicine_code = ?2",
            nativeQuery = true
    )
    public void deleteMedicineQuantityByPharmacy(String regNo, String code);
}
