package app.repository;

import app.domain.DermatologistAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

    @Query(
            value = "select * from dermatologist_appointment da where da.pharmacy_reg_no = ?1",
            nativeQuery = true
    )
    public List<DermatologistAppointment> findFreeAppointmentsByPharmacyRegNo(String regNo);

    @Transactional
    @Modifying
    @Query(
            value="delete from dermatologist_appointment dp where dp.id = ?1",
            nativeQuery = true
    )
    public void deleteDermatologistAppointment(Long id);

    public DermatologistAppointment findOneById(Long id);
}
