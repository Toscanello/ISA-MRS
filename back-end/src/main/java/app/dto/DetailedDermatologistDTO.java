package app.dto;

import app.domain.Dermatologist;

import java.util.ArrayList;
import java.util.List;

public class DetailedDermatologistDTO {
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private List<String> pharmacyRegNos = new ArrayList<>();
    private List<String> pharmacyNames = new ArrayList<>();

    public DetailedDermatologistDTO() {
    }

    public DetailedDermatologistDTO(Dermatologist d) {
        this.email = d.getEmail();
        this.name = d.getName();
        this.surname = d.getSurname();
        this.phoneNumber = d.getPhoneNumber();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getPharmacyRegNos() {
        return pharmacyRegNos;
    }

    public void setPharmacyRegNos(List<String> pharmacyRegNos) {
        this.pharmacyRegNos = pharmacyRegNos;
    }

    public List<String> getPharmacyNames() {
        return pharmacyNames;
    }

    public void setPharmacyNames(List<String> pharmacyNames) {
        this.pharmacyNames = pharmacyNames;
    }
}
