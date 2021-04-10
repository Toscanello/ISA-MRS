package app.repository;

import app.domain.DermatologistAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DermatologistAppointmentRepository extends JpaRepository<DermatologistAppointment, String> {
    @Query(
            value = "select * from dermatologist_appointment da where da.dermatologist_email = ?1",
            nativeQuery = true
    )
    public List<DermatologistAppointment> findFreeAppointmetsByDermatologistEmail(String email);
}
