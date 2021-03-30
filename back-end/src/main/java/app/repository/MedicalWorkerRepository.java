package app.repository;

import app.domain.MedicalWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalWorkerRepository extends JpaRepository<MedicalWorker, String> {
}
