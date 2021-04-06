package app.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FreeAppointmentDTO {
    private String dermatologistEmail;
    private LocalDateTime begin;
    private LocalTime duration;
    private String pharmacyRegNo;
    private double price;

    public FreeAppointmentDTO() {}

    public FreeAppointmentDTO(String dermatologistEmail, LocalDateTime begin, LocalTime duration, String pharmacyRegNo, double price) {
        this.dermatologistEmail = dermatologistEmail;
        this.begin = begin;
        this.duration = duration;
        this.pharmacyRegNo = pharmacyRegNo;
        this.price = price;
    }

    public String getDermatologistEmail() {
        return dermatologistEmail;
    }

    public void setDermatologistEmail(String dermatologistEmail) {
        this.dermatologistEmail = dermatologistEmail;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getPharmacyRegNo() {
        return pharmacyRegNo;
    }

    public void setPharmacyRegNo(String pharmacyRegNo) {
        this.pharmacyRegNo = pharmacyRegNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
