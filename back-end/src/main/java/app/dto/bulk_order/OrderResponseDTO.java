package app.dto.bulk_order;

import app.domain.bulk_order.OrderResponse;

import java.time.format.DateTimeFormatter;

public class OrderResponseDTO {
    private Long id;
    private Long order_id;
    private String availableOnDate;
    private double price;
    private String supplier;
    private OrderResponse.Status status;

    public OrderResponseDTO() { }

    public OrderResponseDTO(OrderResponse or) {
        this.id = or.getId();
        this.order_id = or.getOrder().getId();
        this.supplier = or.getSupplier();
        this.price = or.getPrice();
        this.status = or.getStatus();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.availableOnDate = or.getAvailableOnDate().format(formatter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getAvailableOnDate() {
        return availableOnDate;
    }

    public void setAvailableOnDate(String availableOnDate) {
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

    public OrderResponse.Status getStatus() {
        return status;
    }

    public void setStatus(OrderResponse.Status status) {
        this.status = status;
    }
}
