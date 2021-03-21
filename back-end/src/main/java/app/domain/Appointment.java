package app.domain;

import java.time.LocalDateTime;

public class Appointment {
    private LocalDateTime startTime;
    private LocalDateTime duration;
    private Patient patient;
    private MedicalWorker medicalWorker;
    private Double price;

    public Appointment() {
    }

    public Appointment(LocalDateTime startTime, LocalDateTime duration,
                       Patient patient, MedicalWorker medicalWorker, Double price) {
        this.startTime = startTime;
        this.duration = duration;
        this.patient = patient;
        this.medicalWorker = medicalWorker;
        this.price = price;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getDuration() {
        return duration;
    }

    public void setDuration(LocalDateTime duration) {
        this.duration = duration;
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
}
