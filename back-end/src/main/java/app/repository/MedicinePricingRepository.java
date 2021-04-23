package app.repository;

import app.domain.Medicine;
import app.domain.MedicinePricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicinePricingRepository extends JpaRepository<MedicinePricing, Long> {
    @Query(
            value="select * from medicine_pricing mp where mp.pharmacy_reg_no = ?1",
            nativeQuery = true
    )
    public List<MedicinePricing> findMedicinePricingsByPharmacyRegNo(String regNo);

    @Query(
            value="select * from medicine_pricing mp where mp.pharmacy_reg_no = ?1 and mp.medicine_code = ?2",
            nativeQuery = true
    )
    public List<MedicinePricing> findMedicinePricingsByCodeForPharmacy(String regNo, String code);

    @Query(
            value="select * from medicine_pricing mp " +
                    "where mp.pharmacy_reg_no = ?1 and mp.medicine_code = ?2 and mp.pricing_end is NULL",
            nativeQuery = true
    )
    public MedicinePricing findActivePricingByCodeForPharmacy(String regNo, String code);

    @Transactional
    @Modifying
    @Query(
            value="delete from medicine_pricing mp where mp.pharmacy_reg_no = ?1 and mp.medicine_code = ?2",
            nativeQuery = true
    )
    public void deleteMedicinePricingByPharmacyRegNo(String regNo, String code);
}
