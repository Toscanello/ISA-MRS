package app.repository;

import app.domain.DermatologistAppointment;
import app.domain.MedicineRating;
import app.domain.PharmacistRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PharmacistRatingRepository extends JpaRepository<PharmacistRating, Long> {
    @Modifying
    @Transactional
    @Query(
            value = "insert into pharmacist_rating(patient, pharmacist, rating) values(?1, ?2, ?3)",
            nativeQuery = true
    )
    public void addRating(String patientEmail, String pharmacistEmail, Double rating);

    @Query(
            value = "select * from pharmacist_rating where patient = ?1 and pharmacist = ?2",
            nativeQuery = true
    )
    public PharmacistRating findRatingByEmail(String patientEmail, String pharmacistEmail);

    @Modifying
    @Transactional
    @Query(
            value = "update pharmacist_rating set rating = ?3 where patient = ?1 and pharmacist = ?2",
            nativeQuery = true
    )
    public void updateRating(String patientEmail, String pharmacistEmail, Double rating);

    public List<PharmacistRating> getPharmacistRatingsByPharmacistEmail(String email);
}
