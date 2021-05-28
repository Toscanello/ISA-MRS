package app.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class WorkHourDTO {
    private String workerEmail;
    private String pharmacyRegNo;
    private String startTime;
    private String endTime;

    public WorkHourDTO() {
    }

    public WorkHourDTO(String workerEmail, String pharmacyRegNo, String startTime, String endTime) {
        this.workerEmail = workerEmail;
        this.pharmacyRegNo = pharmacyRegNo;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getWorkerEmail() {
        return workerEmail;
    }

    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }

    public String getPharmacyRegNo() {
        return pharmacyRegNo;
    }

    public void setPharmacyRegNo(String pharmacyRegNo) {
        this.pharmacyRegNo = pharmacyRegNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTimeAsTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(this.startTime, formatter);
    }

    public LocalTime getEndTimeAsTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(this.endTime, formatter);
    }
}
