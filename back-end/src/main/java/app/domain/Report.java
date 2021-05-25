package app.domain;

import app.dto.ReportDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "report", nullable = false)
    private String report;

    @Column(name = "local_date_time", nullable = false)
    private LocalDateTime localDateTime;

    @Column(name = "days", nullable = false)
    private Integer days;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="patient_id",referencedColumnName = "email",nullable = false)
    private Patient patient;

    public Report() {
    }

    public Report(String report, LocalDateTime localDateTime, Integer days, Patient patient) {
        this.report = report;
        this.localDateTime = localDateTime;
        this.days = days;
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
