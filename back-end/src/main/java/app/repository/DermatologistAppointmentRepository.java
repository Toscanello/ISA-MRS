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
    public List<DermatologistAppointment> findFreeAppointmentsByDermatologistEmail(String email);

    @Query(
            value = "select * from dermatologist_appointment da where da.dermatologist_email = ?1 and da.pharmacy_reg_no = ?2",
            nativeQuery = true
    )
    public List<DermatologistAppointment> findFreeAppointmentsByDermatologistEmailAndPharmacy(String email, String pharmacy);
}
