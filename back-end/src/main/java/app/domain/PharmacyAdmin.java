package app.domain;

import javax.persistence.*;

@Entity
@Table(name = "pharmacy_admin")
public class PharmacyAdmin extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_reg_no")
    private Pharmacy pharmacy;

    public PharmacyAdmin() {
        super();
    }

    public PharmacyAdmin(String email, String password, String name, String surname,
                         String phoneNumber, Address address, Pharmacy pharmacy) {
        super(email, password, name, surname, phoneNumber, address);
        this.pharmacy = pharmacy;
    }

    public PharmacyAdmin(String email, String password, String name, String surname,
                         String phoneNumber, Address address) {
        super(email, password, name, surname, phoneNumber, address);
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
