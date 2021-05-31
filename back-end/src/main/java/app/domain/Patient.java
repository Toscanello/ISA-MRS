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

    @ManyToMany
    @JoinTable(name = "patient_pharmacies",
            joinColumns = @JoinColumn(name = "patient_email", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no"))
    private Set<Pharmacy> promotions = new HashSet<>();

    public Patient() {
    }

    public Patient(String email, String password, String name, String surname, String phoneNumber, Address address) {
        super(email, password, name, surname, phoneNumber, address);
    }

    public Set<Pharmacy> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Pharmacy> promotions) {
        this.promotions = promotions;
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
