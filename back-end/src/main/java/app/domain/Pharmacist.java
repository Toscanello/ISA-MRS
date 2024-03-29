package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pharmacist")
public class Pharmacist extends MedicalWorker{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "work_hour",referencedColumnName = "id")
    @JsonIgnore
    private WorkHour workHour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pharmacy",referencedColumnName = "reg_no")
    @JsonIgnore
    private Pharmacy pharmacy;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Pharmacist() {
    }

    public Pharmacist(String email, String password, String name, String surname, String phoneNumber, Address address, WorkHour workHour,Pharmacy pharmacy) {
        super(email, password, name, surname, phoneNumber, address);
        this.workHour = workHour;
        this.pharmacy = pharmacy;
    }

    public WorkHour getWorkHour() {
        return workHour;
    }

    public void setWorkHour(WorkHour workHour) {
        this.workHour = workHour;
    }
}
