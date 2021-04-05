package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "work_hour")
public class WorkHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", unique = false, nullable = false)
    private LocalTime start;

    @Column(name = "end_time", unique = false, nullable = false)
    private LocalTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy",referencedColumnName = "reg_no",nullable = false)
    @JsonIgnore
    private Pharmacy pharmacy;

    public WorkHour() {
    }

    public WorkHour(Long id, LocalTime start, LocalTime end, Pharmacy pharmacy) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.pharmacy = pharmacy;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}
