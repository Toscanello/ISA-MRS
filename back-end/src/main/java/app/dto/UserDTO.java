package app.dto;

import app.domain.Address;
import app.domain.Patient;
import app.domain.User;

public class UserDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private Address address;
    private String phoneNumber;
    public UserDTO() {
    }

    public UserDTO(User user){
        this(user.getEmail(),user.getName(),user.getSurname(), user.getAddress(), user.getPassword(), user.getPhoneNumber());
    }

    public UserDTO(String email, String name, String surname, Address address, String password, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
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
}
