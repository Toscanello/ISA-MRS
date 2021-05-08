package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "medicine_order")
public class MedicineOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine")
    @NotNull
    private MedicinePricing medicine;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    public MedicineOrder() {
    }

    public MedicineOrder(MedicinePricing medicine, Integer quantity, Double price) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public MedicinePricing getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicinePricing medicine) {
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
