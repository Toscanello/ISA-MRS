package app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@Entity
public class DPharmacy {/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regNo;  /* Registry numberm - unique ID

    @Column(name = "name", nullable = false)
    private String name;


    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "dpharmacy_address", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "regNo"),
                                    inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    @NotNull
    private Address address;

    public DPharmacy() { }

    public DPharmacy(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

}
