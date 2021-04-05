package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pharmacist")
public class Pharmacist extends MedicalWorker{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_hour",referencedColumnName = "id",nullable = false)
    @JsonIgnore
    private WorkHour workHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacists_pharmacy", referencedColumnName = "reg_no",nullable = false)
    @JsonIgnore
    Pharmacy pharmacy;

    public Pharmacist() {
    }

    public Pharmacist(String email, String password, String name, String surname, String phoneNumber, Address address, WorkHour workHour, Pharmacy pharmacy) {
        super(email, password, name, surname, phoneNumber, address);
        this.workHour = workHour;
        this.pharmacy = pharmacy;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public WorkHour getWorkHour() {
        return workHour;
    }

    public void setWorkHour(WorkHour workHour) {
        this.workHour = workHour;
    }
}
