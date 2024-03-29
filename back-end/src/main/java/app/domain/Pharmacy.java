package app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pharmacy {
    @Id
    @Column(name = "reg_no", unique = true, nullable = false)
    private String regNo;  /* Registry numberm - unique ID */

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @NotNull
    private Address address;

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacist> pharmacists = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "pharmacy_dermatologists",
            joinColumns = @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no"),
            inverseJoinColumns = @JoinColumn(name = "dermatologist_email", referencedColumnName = "email"))
    private Set<Dermatologist> dermatologists = new HashSet<>();

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<DermatologistAppointment> dermatologistAppointments = new HashSet<>();

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantity> medicineQuantities = new HashSet<>();

    @Column(name = "appointment_price", unique = false, nullable = false)
    private Double appointmentPrice;

    @Version
    private Long version;

    public Pharmacy() { }

    public Pharmacy(String regNo, String name, Address address, Double appointmentPrice) {
        this.regNo = regNo;
        this.name = name;
        this.address = address;
        ratings = new HashSet<>();
        pharmacists = new HashSet<>();
        this.appointmentPrice = appointmentPrice;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<MedicineQuantity> getMedicineQuantities() {
        return medicineQuantities;
    }

    public void setMedicineQuantities(Set<MedicineQuantity> medicineQuantities) {
        this.medicineQuantities = medicineQuantities;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Pharmacist> getPharmacists() {
        return pharmacists;
    }

    public void setPharmacists(Set<Pharmacist> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public Set<Dermatologist> getDermatologists() {
        return dermatologists;
    }

    public void setDermatologists(Set<Dermatologist> dermatologists) {
        this.dermatologists = dermatologists;
    }

    public Set<DermatologistAppointment> getDermatologistAppointments() {
        return dermatologistAppointments;
    }

    public void setDermatologistAppointments(Set<DermatologistAppointment> dermatologistAppointments) {
        this.dermatologistAppointments = dermatologistAppointments;
    }

    public Double getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(Double appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
