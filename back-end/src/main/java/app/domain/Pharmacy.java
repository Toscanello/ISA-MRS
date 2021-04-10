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

    @ManyToMany
    @JoinTable(name = "pharmacy_medicine",
            joinColumns =  @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no"),
            inverseJoinColumns = @JoinColumn(name = "medicine_code", referencedColumnName = "medicine_code"))
    private Set<MedicineQuantity> medicineQuantities = new HashSet<>();

//    private Set<MedicalWorker> medicalWorkers = new HashSet<>();
//    private Set<Appointment> appointments;

    public Pharmacy() { }

    public Pharmacy(String regNo, String name, Address address) {
        this.regNo = regNo;
        this.name = name;
        this.address = address;
//        appointments = new HashSet<>();
//        medicineQuantities = new HashSet<>();
        ratings = new HashSet<>();
        pharmacists = new HashSet<>();
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

//    public Set<MedicalWorker> getMedicalWorkers() {
//        return medicalWorkers;
//    }
//
//    public void setMedicalWorkers(Set<MedicalWorker> medicalWorkers) {
//        this.medicalWorkers = medicalWorkers;
//    }
//
//    public Set<Appointment> getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(Set<Appointment> appointments) {
//        this.appointments = appointments;
//    }
//
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
}
