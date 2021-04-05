package app.repository;
import app.domain.DPharmacy;
import app.domain.Dermatologist;
import app.domain.Pharmacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import javax.transaction.Transactional;
import java.util.Set;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    public Page<Pharmacy> findAll(Pageable pageable);

    @Nullable
    public Pharmacy findPharmacyByRegNo(String regNo);

}
