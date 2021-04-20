package app.dto;

import app.domain.Appointment;
import app.domain.MedicalWorker;
import app.domain.Patient;

import javax.persistence.*;
import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    //private Patient patient;
    //private MedicalWorker medicalWorker;
    private Double price;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Appointment a) {
        this.startTime = a.getStartTime();
        this.endTime = a.getEndTime();
        //this.patient = patient;
        //this.medicalWorker = medicalWorker;
        this.price = a.getPrice();
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

    /*public Patient getPatient() {
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
    }*/

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
