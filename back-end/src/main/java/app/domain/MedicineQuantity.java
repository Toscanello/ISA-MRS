package app.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "medicine_quantity")
public class MedicineQuantity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_code", referencedColumnName = "code")
    private Medicine medicine;

    @Column(name = "quantity", nullable = false)
    private  int quantity;

    public MedicineQuantity() {}

    public MedicineQuantity(Medicine medicine, int quantity) {
        //this.medicine = medicine;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
