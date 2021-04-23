package app.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicine_pricing")
public class MedicinePricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_code", referencedColumnName = "code", nullable = false)
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pharmacy_reg_no", referencedColumnName = "reg_no", nullable = false)
    private Pharmacy pharmacy;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name="pricing_start", nullable = false)
    private LocalDateTime pricingStart;

    @Column(name="pricing_end", nullable = true)    //if pricingEnd == null, the pricing is currently active
    private LocalDateTime pricingEnd;               //pricingEnd sets o a date, after a new active one is added

    public MedicinePricing() {
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

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getPricingStart() {
        return pricingStart;
    }

    public void setPricingStart(LocalDateTime pricingStart) {
        this.pricingStart = pricingStart;
    }

    public LocalDateTime getPricingEnd() {
        return pricingEnd;
    }

    public void setPricingEnd(LocalDateTime pricingEnd) {
        this.pricingEnd = pricingEnd;
    }
}