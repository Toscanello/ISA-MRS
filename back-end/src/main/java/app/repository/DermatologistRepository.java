package app.repository;

import app.domain.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface DermatologistRepository extends JpaRepository<Dermatologist, String > {
    @Query(
            value="select * from users d where d.email in" +
                 "(select pd.dermatologist_email from pharmacy_dermatologists pd where pd.pharmacy_reg_no = ?1)",
            nativeQuery = true
    )
    public List<Dermatologist> findAllDermatologistsByPharmacyRegNo(String regNo);
}
