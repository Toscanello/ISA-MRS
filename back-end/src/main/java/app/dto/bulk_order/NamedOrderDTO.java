package app.dto.bulk_order;

import app.domain.bulk_order.BulkOrder;
import app.domain.bulk_order.BulkOrderItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NamedOrderDTO {
    private Long id;
    private String dueDate;
    private String pharmacyRegNo;
    private List<NamedOrderItemDTO> bulkOrderItemList = new ArrayList<>();

    public NamedOrderDTO() { }

    public NamedOrderDTO(BulkOrder bo) {
        this.id = bo.getId();
        for (BulkOrderItem boi : bo.getOrderItems()) {
            this.bulkOrderItemList.add(new NamedOrderItemDTO(boi));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = bo.getDueDate().format(formatter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPharmacyRegNo() {
        return pharmacyRegNo;
    }

    public void setPharmacyRegNo(String pharmacyRegNo) {
        this.pharmacyRegNo = pharmacyRegNo;
    }

    public List<NamedOrderItemDTO> getBulkOrderItemList() {
        return bulkOrderItemList;
    }

    public void setBulkOrderItemList(List<NamedOrderItemDTO> bulkOrderItemList) {
        this.bulkOrderItemList = bulkOrderItemList;
    }

    public LocalDate getDueDateAsDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(this.dueDate, formatter);
    }
}
