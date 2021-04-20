package app.repository;

import app.domain.MedicalWorker;
import app.domain.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist, String> {

    public Pharmacist findOneByEmail(String email);
}
