package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "medicine_order")
public class MedicineOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine")
    @NotNull
    private MedicinePricing medicine;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="patient_id",referencedColumnName = "email",nullable = false)
    private Patient patient;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "status",nullable = true)
    private boolean status;

    public MedicineOrder() {
    }

    public MedicineOrder(MedicinePricing medicine, Integer quantity, Double price, Patient patient,
                         LocalDateTime startTime, LocalDateTime endTime,boolean status) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.price = price;
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public MedicinePricing getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicinePricing medicine) {
        this.medicine = medicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
