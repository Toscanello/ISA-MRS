package app.dto;

import app.domain.Address;
import app.domain.MedicalWorker;
import app.domain.Role;

public class MedicalWorkerDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private Address address;
    private Role role;

    public MedicalWorkerDTO(MedicalWorker mw) {
        this.email = mw.getEmail();
        this.password = mw.getPassword();
        this.name = mw.getName();
        this.surname = mw.getSurname();
        this.phoneNumber = mw.getPhoneNumber();
        //this.role = mw.getRoles().get(0);
        this.address = mw.getAddress();
    }

    public MedicalWorkerDTO(String email, String password, String name, String surname, String phoneNumber, Address address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
