package app.dto;

import app.domain.Dermatologist;
import app.domain.DermatologistAppointment;
import app.domain.Pharmacy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DermatologistAppointmentDTO {

    private Long id;
    private LocalDateTime time;
    private LocalTime duration;
    private SimpleDermatologistDTO dermatologist;
    //private Pharmacy pharmacy;
    private double price;

    public DermatologistAppointmentDTO(DermatologistAppointment da) {
        this.id = da.getId();
        this.time = da.getTime();
        this.duration = da.getDuration();
        this.dermatologist = new SimpleDermatologistDTO(da.getDermatologist());
        this.price = da.getPrice();
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

    public SimpleDermatologistDTO getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(SimpleDermatologistDTO dermatologist) {
        this.dermatologist = dermatologist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
