package app.repository;
import app.domain.DPharmacy;
import app.domain.Dermatologist;
import app.domain.Pharmacist;
import app.domain.Pharmacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    public Page<Pharmacy> findAll(Pageable pageable);

    @Nullable
    public Pharmacy findPharmacyByRegNo(String regNo);

    @Query(value = "select * from pharmacy p" + " where p.reg_no IN (select a.pharmacy from pharmacist a where " +
            "a.work_hour IN (select w.id from work_hour w where w.start_time<?1 and w.end_time>?2) " +
            "and a.email not in (select appo.medical_worker_id from appointment appo where " +
            "(appo.start_time<=?3 and appo.end_time>=?3)))", nativeQuery = true)
    public List<Pharmacy> findPharmacyPharmacistWorkHour(LocalTime start, LocalTime end, LocalDateTime startDate);

    @Query(
            value = "select ph from Pharmacy ph where ph.regNo in " +
                    "(select a.pharmacy.regNo from PharmacyAdmin a where a.email = ?1)"
    )
    public Pharmacy findPharmacyByAdmin(String email);

    @Query(
            value = "select ph from Pharmacy ph join fetch ph.dermatologists ds" +
                    " where ds.email = ?1"
    )
    public List<Pharmacy> findPharmacyByDermatologist(String email);

    @Query(
            value = "select * from pharmacy ph where ph.reg_no in (select pp.pharmacy_reg_no from patient_pharmacies pp where pp.patient_email = ?1)"
            , nativeQuery = true)
    public List<Pharmacy> getPharmacyAdvertising(String email);

    @Query(
            value = "select * from pharmacy"
            , nativeQuery = true)
    public List<Pharmacy> getAll();

    @Query(
            value = "select * from pharmacy ph where ph.name = ?1 or ?1 in (select a.place from address a where a.id = ph.address_id)"
            , nativeQuery = true)
    public List<Pharmacy> searchPharmacies(String searchParam);

    @Transactional
    @Modifying
    @Query(value = "delete from pharmacy_dermatologists pd " +
            "where pd.pharmacy_reg_no = ?1 and pd.dermatologist_email = ?2",
            nativeQuery = true
    )
    public void deleteDermatologistEmploymentFromPharmacy(String regNo, String email);
}

