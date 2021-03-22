package app.domain;

public class Patient extends User {
    public Patient(String email, String password, String name, String surname, String phoneNumber, Address address) {
        super(email, password, name, surname, phoneNumber, address);
    }
}
