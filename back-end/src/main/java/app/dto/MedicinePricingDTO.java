package app.dto;

import app.domain.MedicinePricing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MedicinePricingDTO {
    private SimpleMedicineDTO medicineDTO;
    private SimplePharmacyDTO pharmacyDTO;
    private Double price;
    private String pricingStart;
    private String pricingEnd;

    public MedicinePricingDTO() { }

    public MedicinePricingDTO(SimpleMedicineDTO medicineDTO, SimplePharmacyDTO pharmacyDTO,
                              Double price, String pricingStart, String pricingEnd) {
        this.medicineDTO = medicineDTO;
        this.pharmacyDTO = pharmacyDTO;
        this.price = price;
        this.pricingStart = pricingStart;
        this.pricingEnd = pricingEnd;
    }

    public MedicinePricingDTO(MedicinePricing mp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        this.medicineDTO = new SimpleMedicineDTO(mp.getMedicine());
        this.pharmacyDTO = new SimplePharmacyDTO(mp.getPharmacy());
        this.price = mp.getPrice();
        this.pricingStart = mp.getPricingStart().format(formatter);
        this.pricingEnd = mp.getPricingEnd() == null ? null : mp.getPricingEnd().format(formatter);
    }

    public SimpleMedicineDTO getMedicineDTO() {
        return medicineDTO;
    }

    public void setMedicineDTO(SimpleMedicineDTO medicineDTO) {
        this.medicineDTO = medicineDTO;
    }

    public SimplePharmacyDTO getPharmacyDTO() {
        return pharmacyDTO;
    }

    public void setPharmacyDTO(SimplePharmacyDTO pharmacyDTO) {
        this.pharmacyDTO = pharmacyDTO;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPricingStart() {
        return pricingStart;
    }

    public void setPricingStart(String pricingStart) {
        this.pricingStart = pricingStart;
    }

    public String getPricingEnd() {
        return pricingEnd;
    }

    public void setPricingEnd(String pricingEnd) {
        this.pricingEnd = pricingEnd;
    }
}
