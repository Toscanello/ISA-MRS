package app.repository;

import app.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    public List<Discount> getAllByMedicineCodeAndPharmacyRegNo(String code, String regNo);

    @Query(
            value = "select dsc from Discount dsc where dsc.medicine.code = ?1 " +
                    "and dsc.pharmacy.regNo = ?2 " +
                    "and dsc.startDate < current_date and dsc.endDate > current_date "
    )
    public Discount getActiveDiscountByMedicineCodeAndPharmacyRegNo(String code, String regNo);
}
