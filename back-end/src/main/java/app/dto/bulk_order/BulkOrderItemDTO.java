package app.dto.bulk_order;

import app.domain.bulk_order.BulkOrderItem;

public class BulkOrderItemDTO {
    private String medicineCode;
    private int amount;

    public BulkOrderItemDTO() { }

    public BulkOrderItemDTO(String medicineCode, int amount) {
        this.medicineCode = medicineCode;
        this.amount = amount;
    }

    public BulkOrderItemDTO(BulkOrderItem boi) {
        this.medicineCode = boi.getMedicine().getCode();
        this.amount = boi.getAmount();
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
