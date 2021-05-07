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
    private Medicine medicine;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_order_list",referencedColumnName = "id",nullable = false)
    @JsonIgnore
    private MedicineOrderList medicineOrderList;

    public MedicineOrder() {
    }

    public MedicineOrder(Medicine medicine, Integer quantity, Double price, MedicineOrderList medicineOrderList) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.price = price;
        this.medicineOrderList = medicineOrderList;
    }

    public Long getId() {
        return id;
    }

    public MedicineOrderList getMedicineOrderList() {
        return medicineOrderList;
    }

    public void setMedicineOrderList(MedicineOrderList medicineOrderList) {
        this.medicineOrderList = medicineOrderList;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
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
