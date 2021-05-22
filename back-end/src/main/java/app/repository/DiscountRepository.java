package app.repository;

import app.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    public List<Discount> getAllByMedicineCodeAndPharmacyRegNo(String code, String regNo);
}
