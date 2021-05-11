package app.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends User {

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MedicineOrder> orders = new HashSet<>();

    public Patient() {
    }

    public Patient(String email, String password, String name, String surname, String phoneNumber, Address address) {
        super(email, password, name, surname, phoneNumber, address);
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<MedicineOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<MedicineOrder> orders) {
        this.orders = orders;
    }
}
