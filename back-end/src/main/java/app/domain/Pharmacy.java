package app.domain;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {
    private String regNo;  /* Registry numberm - unique ID */
    private String name;
    private Address address;
    private List<Integer> ratings;
    private List<String> employeeIDs;
    private List<Appointment> appointments;
    private List<MedicineQuantity> medicineQuantities;

    public Pharmacy() { }

    public Pharmacy(String regNo, String name, Address address) {
        this.regNo = regNo;
        this.name = name;
        this.address = address;
        employeeIDs = new ArrayList<>();
        appointments = new ArrayList<>();
        medicineQuantities = new ArrayList<>();
        ratings = new ArrayList<>();
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

    public List<String> getEmployeeIDs() {
        return employeeIDs;
    }

    public void setEmployeeIDs(List<String> employeeIDs) {
        this.employeeIDs = employeeIDs;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<MedicineQuantity> getMedicineQuantities() {
        return medicineQuantities;
    }

    public void setMedicineQuantities(List<MedicineQuantity> medicineQuantities) {
        this.medicineQuantities = medicineQuantities;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }
}
