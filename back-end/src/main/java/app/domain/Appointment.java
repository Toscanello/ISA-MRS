package app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = true)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = true)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="patient_id",referencedColumnName = "email",nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="medical_worker_id",referencedColumnName = "email",nullable = false)
    private MedicalWorker medicalWorker;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "canceled", nullable = false)
    private boolean isCanceled;

    public Appointment() {
    }

    public Appointment(LocalDateTime startTime, LocalDateTime endTime,
                       Patient patient, MedicalWorker medicalWorker, Double price, boolean isCanceled) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.patient = patient;
        this.medicalWorker = medicalWorker;
        this.price = price;
        this.isCanceled = isCanceled;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public MedicalWorker getMedicalWorker() {
        return medicalWorker;
    }

    public void setMedicalWorker(MedicalWorker medicalWorker) {
        this.medicalWorker = medicalWorker;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isCanceled() { return isCanceled; }

    public void setCanceled(boolean canceled) { isCanceled = canceled; }
}
