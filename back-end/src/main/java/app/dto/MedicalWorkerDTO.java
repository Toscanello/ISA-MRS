package app.dto;

import app.domain.MedicalWorker;

public class MedicalWorkerDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;

    public MedicalWorkerDTO(MedicalWorker mw) {
        this.email = mw.getEmail();
        this.password = mw.getPassword();
        this.name = mw.getName();
        this.surname = mw.getSurname();
        this.phoneNumber = mw.getPhoneNumber();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
