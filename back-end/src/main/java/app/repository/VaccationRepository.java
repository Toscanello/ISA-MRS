package app.repository;

import app.domain.Vaccation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccationRepository extends JpaRepository<Vaccation,Integer> {

    List<Vaccation> findVaccationsByMedicalWorkerEmail(String email);

    Vaccation findVaccationByMedicalWorkerEmail(String email);

}
