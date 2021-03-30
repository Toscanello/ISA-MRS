package app.dto;

import app.domain.Patient;

public class PatientDTO {
    private String email;
    private String name;
    private String surname;

    public PatientDTO() {
    }

    public PatientDTO(Patient patient){
        this(patient.getEmail(),patient.getName(),patient.getSurname());
    }

    public PatientDTO(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
