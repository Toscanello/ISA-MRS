package app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class MedicineRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rating", nullable = false, unique = false)
    private double rating;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient")
    @NotNull
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine")
    @NotNull
    private Medicine medicine;

    public MedicineRating(){}
    public MedicineRating(long id, double rating, Patient patient, Medicine medicine) {
        this.id = id;
        this.rating = rating;
        this.patient = patient;
        this.medicine = medicine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
