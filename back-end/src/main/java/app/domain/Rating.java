package app.domain;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pharmacy pharmacy;

    public Rating() {};
    public Rating(int rating) { this.rating = rating; }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
