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
    @JoinTable(name = "pharmacy_address",
            joinColumns = @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    @NotNull
    private Address address;

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<>();


//    private Set<MedicalWorker> medicalWorkers = new HashSet<>();
//    private Set<Appointment> appointments;
    @ManyToMany
    @JoinTable(name = "pharmacy_medicine",
            joinColumns =  @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no"),
            inverseJoinColumns = @JoinColumn(name = "medicine_code", referencedColumnName = "medicine_code"))
    private Set<MedicineQuantity> medicineQuantities = new HashSet<>();

    public Pharmacy() { }

    public Pharmacy(String regNo, String name, Address address) {
        this.regNo = regNo;
        this.name = name;
        this.address = address;
//        appointments = new HashSet<>();
//        medicineQuantities = new HashSet<>();
        ratings = new HashSet<>();
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
}
