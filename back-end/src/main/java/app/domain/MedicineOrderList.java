package app.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "medicine_order_list")
public class MedicineOrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicineOrder> order;

    @Column(name = "end_time", nullable = false)
    private LocalDate endDate;

    @Column(name = "price", nullable = true)
    private Double price;

    public MedicineOrderList() {
    }

    public MedicineOrderList(List<MedicineOrder> order, LocalDate endDate, Double price) {
        this.order = order;
        this.endDate = endDate;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public List<MedicineOrder> getOrder() {
        return order;
    }

    public void setOrder(List<MedicineOrder> order) {
        this.order = order;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
