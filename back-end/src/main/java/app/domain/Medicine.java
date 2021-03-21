package app.domain;

import java.util.ArrayList;
import java.util.List;

public class Medicine {
    private String name;

    private String code;

    private String type;

    private String manufacturer;

    /* Pastile, Tablete, Prasak */
    private String drugaForm;

    private List<String> alternativeMedicineCodes;

    private String composition;

    private String description;

    /* POM - perscription only, P - pharmacy (no perscription) */
    public enum Category { POM, P }

    private Category category;

    public Medicine() {
    }

    public Medicine(String name, String code, String type, String manufacturer,
                    String drugaForm, List<String> alternativeMedicineCodes, String composition,
                    String description, Category category) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.manufacturer = manufacturer;
        this.drugaForm = drugaForm;
        this.alternativeMedicineCodes = alternativeMedicineCodes;
        this.composition = composition;
        this.description = description;
        this.category = category;
    }

    public Medicine(String name, String code, String type, String manufacturer,
                    String drugaForm, String composition, String description, Category category) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.manufacturer = manufacturer;
        this.drugaForm = drugaForm;
        this.composition = composition;
        this.description = description;
        this.category = category;
        this.alternativeMedicineCodes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDrugaForm() {
        return drugaForm;
    }

    public void setDrugaForm(String drugaForm) {
        this.drugaForm = drugaForm;
    }

    public List<String> getAlternativeMedicineCodes() {
        return alternativeMedicineCodes;
    }

    public void setAlternativeMedicineCodes(List<String> alternativeMedicineCodes) {
        this.alternativeMedicineCodes = alternativeMedicineCodes;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
