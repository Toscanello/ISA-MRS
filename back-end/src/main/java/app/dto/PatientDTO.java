package app.dto;

import app.domain.Address;
import app.domain.Patient;

public class PatientDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private Address address;
    private String phoneNumber;
    private Integer penalty;
    private boolean check;
    public PatientDTO() {
    }

    public PatientDTO(Patient patient){
        this(patient.getEmail(),patient.getName(),patient.getSurname(), patient.getAddress(), patient.getPassword(), patient.getPhoneNumber(), patient.getPenalty());
    }

    public PatientDTO(String email, String name, String surname, Address address, String password, String phoneNumber, Integer penalty) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.check = false;
        this.penalty = penalty;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }
}
