package app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class DermatologistRating {
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
    @JoinColumn(name = "dermatologist")
    @NotNull
    private Dermatologist dermatologist;

    public DermatologistRating(){}
    public DermatologistRating(long id, double rating, Patient patient, Dermatologist dermatologist) {
        this.id = id;
        this.rating = rating;
        this.patient = patient;
        this.dermatologist = dermatologist;
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

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }
}

