package app.dto;

import app.domain.MedicineOrder;
import java.time.LocalDateTime;

public class MedicineOrderDTO {
    private Long id;
    private MedicinePricingDTO medicine;
    private Integer quantity;
    private Double price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public MedicineOrderDTO() {
    }

    public MedicineOrderDTO(MedicineOrder medicineOrder) {
        this.id = medicineOrder.getId();
        this.medicine = new MedicinePricingDTO(medicineOrder.getMedicine());
        this.quantity = medicineOrder.getQuantity();
        this.price = medicineOrder.getPrice();
        this.startTime = medicineOrder.getStartTime();
        this.endTime = medicineOrder.getEndTime();
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
