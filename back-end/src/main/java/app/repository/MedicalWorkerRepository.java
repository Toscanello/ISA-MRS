package app.repository;

import app.domain.Appointment;
import app.domain.MedicalWorker;
import app.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalWorkerRepository extends JpaRepository<MedicalWorker, String> {
    public MedicalWorker findOneByEmail(String email);
}
