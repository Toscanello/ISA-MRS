package app.repository;

import app.domain.Vaccation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccationRepository extends JpaRepository<Vaccation,Integer> {

}
