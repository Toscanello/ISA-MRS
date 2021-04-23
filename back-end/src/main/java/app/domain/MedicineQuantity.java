package app.domain;

import javax.persistence.*;

@Entity
@Table(
        name = "medicine_quantity",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"medicine_code", "pharmacy_reg_no"})}
)
public class MedicineQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_code", referencedColumnName = "code")
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no")
    private Pharmacy pharmacy;

    @Column(name = "quantity", nullable = false)
    private  int quantity;

    public MedicineQuantity() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
