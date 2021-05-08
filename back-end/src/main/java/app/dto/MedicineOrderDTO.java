package app.dto;

import app.domain.MedicineOrder;
import app.domain.MedicinePricing;
import app.domain.Patient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class MedicineOrderDTO {
    private  Long id;
    private MedicinePricingDTO medicine;
    private Integer quantity;
    private Double price;

    public MedicineOrderDTO() {
    }

    public MedicineOrderDTO(MedicineOrder medicineOrder) {
        this.medicine = new MedicinePricingDTO(medicineOrder.getMedicine());
        this.quantity = medicineOrder.getQuantity();
        this.price = medicineOrder.getPrice();
    }

    public Long getId() {
        return id;
    }

    public MedicinePricingDTO getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicinePricingDTO medicine) {
        this.medicine = medicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
