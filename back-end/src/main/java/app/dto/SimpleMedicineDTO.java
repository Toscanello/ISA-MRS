package app.dto;

import app.domain.Medicine;

public class SimpleMedicineDTO {
    private String code;
    private String name;
    private String type;
    private String manufacturer;
    private String drugForm;
    private String composition;
    private String description;
    private Medicine.Category category;

    public SimpleMedicineDTO() { }

    public SimpleMedicineDTO(String code, String name, String type, String manufacturer, String drugForm,
                             String composition, String description, Medicine.Category category) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.drugForm = drugForm;
        this.composition = composition;
        this.description = description;
        this.category = category;
    }

    public SimpleMedicineDTO(Medicine m) {
        this.code = m.getCode();
        this.name = m.getName();
        this.type = m.getType();
        this.manufacturer = m.getManufacturer();
        this.drugForm = m.getDrugForm();
        this.composition = m.getComposition();
        this.description = m.getDescription();
        this.category = m.getCategory();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(String drugForm) {
        this.drugForm = drugForm;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Medicine.Category getCategory() {
        return category;
    }

    public void setCategory(Medicine.Category category) {
        this.category = category;
    }
}
