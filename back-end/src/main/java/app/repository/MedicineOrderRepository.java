package app.repository;

import app.domain.MedicineOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicineOrderRepository extends JpaRepository<MedicineOrder, String> {

    @Query(value = "select * from medicine_order mo where mo.patient_id = ?1",
            nativeQuery = true)
    public List<MedicineOrder> findPatientsMedicineOrders(String email);
}
