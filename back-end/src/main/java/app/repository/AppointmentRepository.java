package app.repository;

import app.domain.Appointment;
import app.domain.DermatologistAppointment;
import app.domain.Patient;
import app.domain.Pharmacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    public Page<Appointment> findAll(Pageable pageable);

    @Query(
            value="select * from appointment a where a.medical_worker_id = ?1",
            nativeQuery = true
    )
    public List<Appointment> findAllByMedicalWorkerEmail(String email);

    @Query(
            value="select * from appointment a where a.patient_id = ?1",
            nativeQuery = true
    )
    public List<Appointment> findAllByPatientId(String email);

    @Query(
            value="select * from appointment a where a.patient_id = ?1 and a.canceled = false",
            nativeQuery = true
    )
    public List<Appointment> findActiveAppointmentsByPatientId(String email);

    @Query(
            value="select * from appointment a where a.medical_worker_id = ?1 and a.canceled = false",
            nativeQuery = true
    )
    public List<Appointment> findActiveAppointmentsByPharmacistId(String email);

    @Modifying
    @Transactional
    @Query(
            value="update appointment set canceled = true where id = ?1",
            nativeQuery = true
    )
    public void cancelAppointment(Long id);

    @Query(
            value="select * from appointment a where a.medical_worker_id = ?1 and a.canceled = false and a.pharmacy_reg_no = ?2",
            nativeQuery = true
    )
    List<Appointment> findActiveAppointmentsByDermatologist(String email, String pharmacy);

    public Appointment findOneById(Long id);

    @Query(
            value = "select * from appointment a where a.patient_id = ?1 and a.medical_worker_id in (select user_id from user_role ur where ur.role_id = 3)",
            nativeQuery = true
    )
    public List<Appointment> findPatientsAppointments(String email);


    @Query(
            value = "select a from Appointment a where a.pharmacy.regNo = ?1 and a.isCanceled = false"
    )
    public List<Appointment> findActiveAppointmentsByPharmacy(String regNo);
}
