package app.dto;

import app.domain.DermatologistAppointment;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FreeAppointmentDTO {
    private Long id;
    private String dermatologistEmail;
    private String begin;
    private String duration;
    private String pharmacyRegNo;
    private double price;

    public FreeAppointmentDTO() {}

    public FreeAppointmentDTO(Long id,String dermatologistEmail, String begin, String duration, String pharmacyRegNo, double price) {
        this.id=id;
        this.dermatologistEmail = dermatologistEmail;
        this.begin = begin;
        this.duration = duration;
        this.pharmacyRegNo = pharmacyRegNo;
        this.price = price;
    }
    public FreeAppointmentDTO(DermatologistAppointment a){
        this.id=a.getId();
        this.dermatologistEmail = a.getDermatologist().getEmail();
        this.begin = a.getTime().toString();
        this.duration = a.getDuration().toString();
        this.pharmacyRegNo = a.getPharmacy().getRegNo();
        this.price = a.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
