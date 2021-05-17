package app.dto;

import app.domain.Address;
import app.domain.Appointment;
import app.domain.MedicalWorker;
import app.domain.Patient;
import app.dto.PatientDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private PatientDTO patient;
    private MedicalWorkerDTO medicalWorker;
    private Double price;
    private Address address;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long id) {
        this.id = id;
    }

    public AppointmentDTO(Appointment a) {
        this.id = a.getId();
        this.startTime = a.getStartTime();
        this.endTime = a.getEndTime();
        this.patient = new PatientDTO(a.getPatient());
        this.medicalWorker = new MedicalWorkerDTO(a.getMedicalWorker());
        this.price = a.getPrice();
        this.address = a.getMedicalWorker().getAddress();

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public MedicalWorkerDTO getMedicalWorker() {
        return medicalWorker;
    }

    public void setMedicalWorker(MedicalWorkerDTO medicalWorker) {
        this.medicalWorker = medicalWorker;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
