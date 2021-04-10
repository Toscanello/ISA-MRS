package app.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FreeAppointmentDTO {
    private String dermatologistEmail;
    private String begin;
    private String duration;
    private String pharmacyRegNo;
    private double price;

    public FreeAppointmentDTO() {}

    public FreeAppointmentDTO(String dermatologistEmail, String begin, String duration, String pharmacyRegNo, double price) {
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

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
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
