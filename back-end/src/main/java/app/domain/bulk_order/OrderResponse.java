package app.domain.bulk_order;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "order_response")
public class OrderResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private BulkOrder order;

    @Column(name = "available_date")
    private LocalDate availableOnDate;

    @Column(name = "price")
    private double price;

    @Column(name = "supplier")
    private String supplier;

    public enum Status { PENDING, ACCEPTED, DECLINED }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Version
    private Long version;

    public OrderResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BulkOrder getOrder() {
        return order;
    }

    public void setOrder(BulkOrder order) {
        this.order = order;
    }

    public LocalDate getAvailableOnDate() {
        return availableOnDate;
    }

    public void setAvailableOnDate(LocalDate availableOnDate) {
        this.availableOnDate = availableOnDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
