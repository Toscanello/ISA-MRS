package app.dto;

import app.domain.Address;
import app.domain.Pharmacy;
import app.domain.Rating;

import java.util.ArrayList;
import java.util.List;

public class SimplePharmacyDTO {
    private String regNo;
    private String name;
    private Address address;
    private List<Integer> ratings = new ArrayList<>();

    public SimplePharmacyDTO() {}

    public SimplePharmacyDTO(Pharmacy p) {
        this.regNo = p.getRegNo();
        this.name = p.getName();
        this.address = p.getAddress();
        for (Rating r : p.getRatings())
            ratings.add(r.getRating());
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }
}
