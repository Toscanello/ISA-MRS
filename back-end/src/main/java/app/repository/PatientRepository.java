package app.repository;

import app.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, String>{

    public Page<Patient> findAll(Pageable pageable);

    @Query(value = "select * from users p" +
            " where p.email IN (select a.patient_id from appointment a where a.medical_worker_id = ?1)", nativeQuery = true)
    public List<Patient> findAllStudentsByPharmacistEmail(String email);

}
