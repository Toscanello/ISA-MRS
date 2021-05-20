package app.domain.bulk_order;

import app.domain.Pharmacy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "bulk_order")
public class BulkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<BulkOrderItem> orderItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no")
    private Pharmacy pharmacy;

    public  BulkOrder() { }

    public void addItem(BulkOrderItem boi) {
        this.orderItems.add(boi);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Set<BulkOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<BulkOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
