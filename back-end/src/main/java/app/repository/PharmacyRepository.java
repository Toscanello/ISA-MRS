package app.repository;
import app.domain.DPharmacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<DPharmacy, Long> {
    public Page<DPharmacy> findAll(Pageable pageable);
}
