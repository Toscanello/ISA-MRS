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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no", nullable = false)
    private Pharmacy pharmacy;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "canceled", nullable = false)
    private boolean isCanceled;

    @Column(name="finished",nullable = false)
    private boolean finished;

    @Column(name = "appearance",nullable = false)
    private boolean appearance;

    @Version
    private Long version;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Appointment() {
    }

    public Appointment(LocalDateTime startTime, LocalDateTime endTime,
                       Patient patient, MedicalWorker medicalWorker, Pharmacy pharmacy,
                       Double price, boolean isCanceled,boolean finished, boolean appearance) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.patient = patient;
        this.medicalWorker = medicalWorker;
        this.pharmacy = pharmacy;
        this.price = price;
        this.isCanceled = isCanceled;
        this.finished=finished;
        this.appearance = appearance;
    }

    public Long getId() { return id; }

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

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isCanceled() { return isCanceled; }

    public void setCanceled(boolean canceled) { isCanceled = canceled; }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isAppearance() {
        return appearance;
    }

    public void setAppearance(boolean appearance) {
        this.appearance = appearance;
    }
}
