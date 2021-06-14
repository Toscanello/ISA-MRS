package app.repository;

import app.domain.MedicineOrder;
import app.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, String>{

    public Page<Patient> findAll(Pageable pageable);

    @Query(value = "select * from users u " +
            "full outer join patients p on u.email = p.email" +
            " where u.email IN (select a.patient_id from appointment a where a.medical_worker_id = ?1)",
            nativeQuery = true)
    public List<Patient> findAllStudentsByMedicalEmail(String email);

    @Query(value = "select * from users p full outer join patients pat on p.email = pat.email" +
            " where p.email IN (select a.patient_id from appointment a where a.medical_worker_id = :email)" +
            "and (p.name is null or :name ='null'  or :name = '' or p.name = :name)" +
            "and (p.surname = 'null' or :surname = 'null'  or :surname = '' or p.surname = :surname)",
            nativeQuery = true)
    public List<Patient> searchPatients(String email, String name, String surname);

    public Patient findOneByEmail(String email);

    @Query(value = "select * from users p full outer join patients pat on p.email = pat.email " +
            "where p.email in (select s.patient_email from patient_pharmacies s where pharmacy_reg_no = ?1)",
            nativeQuery = true)
    public List<Patient> findAllSubscribedToPharmacy(String regNo);

    @Transactional
    @Modifying
    @Query(value = "insert into patient_allergies(patient_id, medicine_code) values(?1, ?2)",
            nativeQuery = true)
    public void insertAllergy(String patient_email, String medicine_code);

    @Query(value = "select patient_id from patient_allergies where patient_id = ?1 and medicine_code = ?2",
            nativeQuery = true)
    public String findAllergy(String patient_email, String medicine_code);

    @Transactional
    @Modifying
    @Query(value = "delete from patient_allergies where patient_id = ?1 and medicine_code = ?2", nativeQuery = true)
    public void deleteAllergy(String patient_email, String medicine_code);

    @Transactional
    @Modifying
    @Query(value = "update patients set penalty = ?2 where email = ?1",nativeQuery = true)
    void addPenalty(String email,Integer num);
}
