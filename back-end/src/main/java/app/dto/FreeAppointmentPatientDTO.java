package app.dto;

public class FreeAppointmentPatientDTO extends FreeAppointmentDTO{
    private String patientEmail;

    public FreeAppointmentPatientDTO(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public FreeAppointmentPatientDTO(String dermatologistEmail, String begin, String duration, String pharmacyRegNo, double price, String patientEmail) {
        super(dermatologistEmail, begin, duration, pharmacyRegNo, price);
        this.patientEmail = patientEmail;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }
}
