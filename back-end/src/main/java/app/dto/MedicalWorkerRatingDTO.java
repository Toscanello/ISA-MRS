package app.dto;

import app.domain.DermatologistRating;
import app.domain.PharmacistRating;

public class MedicalWorkerRatingDTO {
    private String medicalWorkerEmail;
    private double rating;

    public MedicalWorkerRatingDTO(PharmacistRating pharmacistRating) {
        this.rating = pharmacistRating.getRating();
        this.medicalWorkerEmail = pharmacistRating.getPharmacist().getEmail();
    }

    public MedicalWorkerRatingDTO(DermatologistRating dermatologistRating) {
        this.rating = dermatologistRating.getRating();
        this.medicalWorkerEmail = dermatologistRating.getDermatologist().getEmail();
    }

    public MedicalWorkerRatingDTO() { }

    public String getMedicalWorkerEmail() {
        return medicalWorkerEmail;
    }

    public void setMedicalWorkerEmail(String medicalWorkerEmail) {
        this.medicalWorkerEmail = medicalWorkerEmail;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
