package app.dto;

import app.domain.Discount;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiscountDTO {
    private String medicineCode;
    private String pharmacyRegNo;
    private String startDate;
    private String endDate;
    private double percentage;

    public DiscountDTO() {
    }

    public DiscountDTO(String medicineCode, String pharmacyRegNo, String startDate, String endDate, double percentage) {
        this.medicineCode = medicineCode;
        this.pharmacyRegNo = pharmacyRegNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
    }

    public DiscountDTO(Discount d) {
        this.medicineCode = d.getMedicine().getCode();
        this.pharmacyRegNo = d.getPharmacy().getRegNo();
        this.percentage = d.getPercentage();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.startDate = d.getStartDate().format(formatter);
        this.endDate = d.getEndDate().format(formatter);
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getPharmacyRegNo() {
        return pharmacyRegNo;
    }

    public void setPharmacyRegNo(String pharmacyRegNo) {
        this.pharmacyRegNo = pharmacyRegNo;
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public LocalDate getStartDateAsDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(this.startDate, formatter);
    }

    public LocalDate getEndDateAsDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(this.endDate, formatter);
    }
}
