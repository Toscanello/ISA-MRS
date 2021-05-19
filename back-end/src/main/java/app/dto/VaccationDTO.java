package app.dto;

import app.domain.MedicalWorker;
import app.domain.Vaccation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VaccationDTO {
    private String medicalWorkerId;
    private String startDate;
    private String endDate;
    private String status;

    public VaccationDTO() {
    }

    public VaccationDTO(String medicalWorkerId, String startDate, String endDate, String status) {
        this.medicalWorkerId = medicalWorkerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public VaccationDTO(Vaccation v) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = v.getStartDate().format(formatter);
        String endDate = v.getEndDate().format(formatter);
        this.medicalWorkerId = v.getMedicalWorker().getEmail();
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = v.getStatus().toString();
    }

    public String getMedicalWorkerId() {
        return medicalWorkerId;
    }

    public void setMedicalWorkerId(String medicalWorkerId) {
        this.medicalWorkerId = medicalWorkerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
