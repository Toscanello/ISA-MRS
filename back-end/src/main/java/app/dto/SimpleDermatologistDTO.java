package app.dto;

import app.domain.Dermatologist;

import java.time.LocalTime;

public class SimpleDermatologistDTO {
    private String email;
    private String name;
    private String surname;

    public SimpleDermatologistDTO() {}

    public SimpleDermatologistDTO(Dermatologist d) {
        this.email = d.getEmail();
        this.name = d.getName();
        this.surname = d.getSurname();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
