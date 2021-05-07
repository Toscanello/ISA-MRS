package app.dto;

import app.domain.Dermatologist;
import app.domain.Pharmacist;

public class PharmacistDTO {
    private String email;
    private String name;
    private String surname;

    public PharmacistDTO() {}

    public PharmacistDTO(Pharmacist p) {
        this.email = p.getEmail();
        this.name = p.getName();
        this.surname = p.getSurname();
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
