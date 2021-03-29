package app.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medicine {
    @Id
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "type", unique = false, nullable = false)
    private String type;

    @Column(name = "manufacturer", unique = false, nullable = false)
    private String manufacturer;

    /* Pastile, Tablete, Prasak */
    @Column(name = "drug_form", unique = false, nullable = false)
    private String drugaForm;

    @ManyToMany
    @JoinTable(name = "alternative_medicine",
    joinColumns = @JoinColumn(name = "medicine_code", referencedColumnName = "code"),
    inverseJoinColumns = @JoinColumn(name = "alternative_code", referencedColumnName = "code"))
    private List<Medicine> alternativeMedicine = new ArrayList<>();

    @Column(name = "composition", unique = false, nullable = false)
    private String composition;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    /* POM - perscription only, P - pharmacy (no perscription) */
    public enum Category { POM, P }

    @Enumerated(EnumType.STRING)
    private Category category;

    public Medicine() {
    }

    public Medicine(String name, String code, String type, String manufacturer,
                    String drugaForm, List<Medicine> alternativeMedicine, String composition,
                    String description, Category category) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.manufacturer = manufacturer;
        this.drugaForm = drugaForm;
        this.alternativeMedicine = alternativeMedicine;
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

    public List<Medicine> getAlternativeMedicine() {
        return alternativeMedicine;
    }

    public void setAlternativeMedicine(List<Medicine> alternativeMedicine) {
        this.alternativeMedicine = alternativeMedicine;
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
