package app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PharmacistRating {
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
    @JoinColumn(name = "pharmacist")
    @NotNull
    private Pharmacist pharmacist;

    public PharmacistRating(){}
    public PharmacistRating(long id, double rating, Patient patient, Pharmacist pharmacist) {
        this.id = id;
        this.rating = rating;
        this.patient = patient;
        this.pharmacist = pharmacist;
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

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}


