package app.domain.bulk_order;

import app.domain.Medicine;
import app.domain.Pharmacy;

import javax.persistence.*;

@Entity(name = "bulk_order_item")
public class BulkOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_code", referencedColumnName = "code")
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private BulkOrder order;

    @Column(name = "amount", nullable = false)
    private int amount;

    public BulkOrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BulkOrder getBulkOrder() {
        return order;
    }

    public void setBulkOrder(BulkOrder bulkOrder) {
        this.order = bulkOrder;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
