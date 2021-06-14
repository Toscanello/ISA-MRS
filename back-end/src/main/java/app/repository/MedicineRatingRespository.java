package app.repository;

import app.domain.MedicineQuantity;
import app.domain.MedicineRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface MedicineRatingRespository extends JpaRepository<MedicineRating, Long> {
    @Modifying
    @Transactional
    @Query(
            value = "insert into medicine_rating(patient, medicine, rating) values(?1, ?2, ?3)",
            nativeQuery = true
    )
    public void addRating(String patientEmail, String medicineCode, Double rating);

    @Query(
            value = "select * from medicine_rating where patient = ?1 and medicine = ?2",
            nativeQuery = true
    )
    public MedicineRating findRatingByEmail(String patientEmail, String medicineCode);

    @Modifying
    @Transactional
    @Query(
            value = "update medicine_rating set rating = ?3 where patient = ?1 and medicine = ?2",
            nativeQuery = true
    )
    public void updateRating(String patientEmail, String medicineCode, Double rating);
}
