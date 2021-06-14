package app.repository;

import app.domain.DermatologistAppointment;
import app.domain.DermatologistRating;
import app.domain.MedicineRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DermatologistRatingRepository extends JpaRepository<DermatologistRating, Long> {
    @Modifying
    @Transactional
    @Query(
            value = "insert into dermatologist_rating(patient, dermatologist, rating) values(?1, ?2, ?3)",
            nativeQuery = true
    )
    public void addRating(String patientEmail, String dermatologistEmail, Double rating);

    @Query(
            value = "select * from dermatologist_rating where patient = ?1 and dermatologist = ?2",
            nativeQuery = true
    )
    public DermatologistRating findRatingByEmail(String patientEmail, String dermatologistEmail);

    @Modifying
    @Transactional
    @Query(
            value = "update dermatologist_rating set rating = ?3 where patient = ?1 and dermatologist = ?2",
            nativeQuery = true
    )
    public void updateRating(String patientEmail, String dermatologistEmail, Double rating);
}
