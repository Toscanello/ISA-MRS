package app.repository;

import app.domain.DermatologistWorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DermatologistWorkHourRepository extends JpaRepository<DermatologistWorkHour, String> {
    @Query(
            value = "select * from d_work_hour wh where wh.dermatologist_email = ?1",
            nativeQuery = true
    )
    public List<DermatologistWorkHour> findWorkHoursByDermatologistEmail(String email);
}
