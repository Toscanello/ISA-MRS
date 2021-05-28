package app.dto;

import app.domain.Dermatologist;
import app.domain.Pharmacist;

public class PharmacistDTO {
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String pharmacyRegNo;
    private String pharmacyName;

    public PharmacistDTO() {}

    public PharmacistDTO(Pharmacist p) {
        this.email = p.getEmail();
        this.name = p.getName();
        this.surname = p.getSurname();
        this.phoneNumber = p.getPhoneNumber();
        this.pharmacyRegNo = p.getPharmacy() == null ? null : p.getPharmacy().getRegNo();
        this.pharmacyName = p.getPharmacy() == null ? null: p.getPharmacy().getName();
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

    public String getPharmacyRegNo() {
        return pharmacyRegNo;
    }

    public void setPharmacyRegNo(String pharmacyRegNo) {
        this.pharmacyRegNo = pharmacyRegNo;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
