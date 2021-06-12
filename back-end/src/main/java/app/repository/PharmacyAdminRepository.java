package app.repository;

import app.domain.MedicineOrder;
import app.domain.PharmacyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyAdminRepository extends JpaRepository<PharmacyAdmin, String> {
    public List<PharmacyAdmin> findAllByPharmacyRegNo(String pharmacy_reg_no);
}
