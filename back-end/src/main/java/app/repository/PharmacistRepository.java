package app.repository;

import app.domain.MedicalWorker;
import app.domain.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface PharmacistRepository extends JpaRepository<Pharmacist, String> {

    public Pharmacist findOneByEmail(String email);

    /*
    +
            "select * from pharmacist a where a.work_hour IN (select w.id from work_hour w where w.start_time<?1 and w.end_time>?2 and w.pharmacy = ?4) " +
            "and a.email not in (select appo.medical_worker_id from appointment appo where " +
            "(appo.start_time<=?3 and appo.end_time>=?3))"
    * */
    /*
    *value="select * from users d where d.email in" +
                    "(select pd.dermatologist_email from pharmacy_dermatologists pd where pd.pharmacy_reg_no = ?1)",
    *
    * */
    @Query(value = "select a.email from pharmacist a where a.work_hour IN (select w.id from work_hour w where w.start_time<?1 and w.end_time>?2 and w.pharmacy = ?4) " +
            "and a.email not in (select appo.medical_worker_id from appointment appo where " +
            "(appo.start_time<=?3 and appo.end_time>=?3))", nativeQuery = true)
    public List<String> findPharmacist(LocalTime start, LocalTime end, LocalDateTime startDate, String pharmacyRegNo);

    public List<Pharmacist> findPharmacistsByPharmacyRegNo(String regNo);

    @Query(value = "select p from Pharmacist p where p.pharmacy is NULL")
    public List<Pharmacist> getUnemployed();

    @Query(value = "select a.email from pharmacist a where a.email IN " +
            "(select appo.medical_worker_id from appointment appo where appo.patient_id = ?1 and appo.appearance = true)", nativeQuery = true)
    public Set<String> findPharmacistForRating(String patientEmail);

}
