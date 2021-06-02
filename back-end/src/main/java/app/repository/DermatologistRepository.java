package app.repository;

import app.domain.Appointment;
import app.domain.Dermatologist;
import app.domain.DermatologistAppointment;
import app.domain.DermatologistWorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DermatologistRepository extends JpaRepository<Dermatologist, String > {
    @Query(
            value="select * from users d where d.email in" +
                    "(select pd.dermatologist_email from pharmacy_dermatologists pd where pd.pharmacy_reg_no = ?1)",
            nativeQuery = true
    )
    public List<Dermatologist> findAllDermatologistsByPharmacyRegNo(String regNo);


    @Query(
            value = "select * from d_work_hour wh where wh.dermatologist_email = ?1",
            nativeQuery = true
    )
    public List<DermatologistWorkHour> findWorkHoursByDermatologistEmail(String email);


    @Query(
            value = "select * from dermatologist_appointment da where da.dermatologist_email = ?1",
            nativeQuery = true
    )
    public List<DermatologistAppointment> findFreeAppointmetsByDermatologistEmail(String email);


    @Query(
            value="select * from appointment a where a.medical_worker_id = ?1",
            nativeQuery = true
    )
    public List<Appointment> findAllAppointmentsByDermatologistEmail(String email);

    @Transactional
    @Modifying
    @Query(
            value = "insert into pharmacy_dermatologists(pharmacy_reg_no, dermatologist_email) " +
                    "values(?2, ?1)",
            nativeQuery = true
    )
    public void addEmployement(String email, String regNo);

    public Dermatologist findDermatologistByEmail(String email);

}
