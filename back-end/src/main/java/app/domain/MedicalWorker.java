package app.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medical_workers")
public class MedicalWorker extends User {

    @OneToMany(mappedBy = "medicalWorker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    public MedicalWorker() {
    }

    public MedicalWorker(String email, String password, String name, String surname, String phoneNumber, Address address) {
        super(email, password, name, surname, phoneNumber, address);
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
