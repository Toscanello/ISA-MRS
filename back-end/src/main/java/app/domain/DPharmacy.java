package app.domain;

import javax.persistence.*;

@Entity
public class DPharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regNo;  /* Registry numberm - unique ID */
    @Column(name = "name", nullable = false)
    private String name;

    public DPharmacy() { }

    public DPharmacy(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
