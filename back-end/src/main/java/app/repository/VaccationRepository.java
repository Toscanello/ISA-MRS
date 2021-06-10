package app.repository;

import app.domain.Vaccation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VaccationRepository extends JpaRepository<Vaccation,Integer> {

    List<Vaccation> findVaccationsByMedicalWorkerEmail(String email);

    @Query(
            value = "select v from Vaccation v " +
                    "where v.medicalWorker.email = ?1 and v.status = 'STANDBY'"
    )
    Vaccation findVaccationByMedicalWorkerEmail(String email);

}
