package app.repository;

import app.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    public List<Medicine> findAll();

    public Medicine getMedicineByCode(String code);

    @Query(
            value = "select * from medicine m where m.code in (" +
                    "select medicine_code from medicine_quantity mq where mq.pharmacy_reg_no = ?1)",
            nativeQuery = true
    )
    public List<Medicine> findMedicineByPharmacyRegNo(String regNo);

    @Query(value = "select * from medicine m where m.code in (select medicine_code from patient_allergies where patient_id = ?1)",
            nativeQuery = true)
    public List<Medicine> findPatientsAllergies(String patient_email);
}
