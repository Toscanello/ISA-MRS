package app.dto.bulk_order;

import app.domain.bulk_order.BulkOrderItem;

public class NamedOrderItemDTO {
    private String medicineCode;
    private String medicineName;
    private int amount;

    public NamedOrderItemDTO() { }

    public NamedOrderItemDTO(String medicineCode, String medicineName, int amount) {
        this.medicineCode = medicineCode;
        this.medicineName = medicineName;
        this.amount = amount;
    }

    public NamedOrderItemDTO(BulkOrderItem boi) {
        this.medicineCode = boi.getMedicine().getCode();
        this.medicineName = boi.getMedicine().getName();
        this.amount = boi.getAmount();
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
