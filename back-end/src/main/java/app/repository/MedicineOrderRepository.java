package app.repository;

import app.domain.MedicineOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicineOrderRepository extends JpaRepository<MedicineOrder, Long> {

    @Query(value = "select * from medicine_order mo where mo.patient_id = ?1",
            nativeQuery = true)
    public List<MedicineOrder> findPatientsMedicineOrders(String email);

    @Transactional
    @Modifying
    @Query(
            value = "insert into medicine_order(medicine, quantity, price," +
                    "patient_id, start_time, end_time, status) " +
                    "values (?1, ?2, ?3, ?4, ?5, ?6,true)",
            nativeQuery = true
    )
    public void insertNewOrder(Long medicinePricingId, int quantity,
                               double price, String patientEmail, LocalDateTime start, LocalDateTime end);

    @Query(value = "select * from medicine_order mo where mo.medicine in (select mp.id from medicine_pricing mp where mp.pharmacy_reg_no = ?1) and mo.status =false",
            nativeQuery = true)
    public List<MedicineOrder> findAllByPharmacyRegNo(String regno);


    @Modifying
    @Transactional
    @Query(
            value="update medicine_order set status = true where id = ?1",
            nativeQuery = true
    )
    public void update(Long id);


}
