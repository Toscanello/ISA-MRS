package app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class DermatologistAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "duration")
    private LocalTime duration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dermatologist_email",referencedColumnName = "email",nullable = false)
    private Dermatologist dermatologist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no", nullable = false)
    private Pharmacy pharmacy;

    @Column(name = "price")
    private double price;

    public DermatologistAppointment() {}

    public DermatologistAppointment(LocalDateTime time, LocalTime duration, Dermatologist dermatologist, Pharmacy pharmacy, double price) {
        this.time = time;
        this.duration = duration;
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
