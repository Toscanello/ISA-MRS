package app.domain;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "work_hour")
public class WorkHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatologist_email")
    private Dermatologist dermatologist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_reg_no")
    private Pharmacy pharmacy;

    @Column(name = "beggining_hour")
    private LocalTime begginingHour;

    @Column(name = "ending_hour")
    private LocalTime ending_hour;

    public WorkHour() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public LocalTime getBegginingHour() {
        return begginingHour;
    }

    public void setBegginingHour(LocalTime begginingHour) {
        this.begginingHour = begginingHour;
    }

    public LocalTime getEnding_hour() {
        return ending_hour;
    }

    public void setEnding_hour(LocalTime ending_hour) {
        this.ending_hour = ending_hour;
    }
}
