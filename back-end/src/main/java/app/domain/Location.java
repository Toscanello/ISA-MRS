package app.domain;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "width", nullable = false)
    private double geoWidth;

    @Column(name = "height", nullable = false)
    private double geoHeight;

    public Location() { }

    public Location(double geoWidth, double geoHeight)
    {
        this.geoWidth = geoWidth;
        this.geoHeight = geoHeight;
    }

    public double getGeoWidth() {
        return geoWidth;
    }

    public void setGeoWidth(double geoWidth) {
        this.geoWidth = geoWidth;
    }

    public double getGeoHeight() {
        return geoHeight;
    }

    public void setGeoHeight(double geoHeight) {
        this.geoHeight = geoHeight;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return "Location{" +
                "geoWidth='" + geoWidth + '\'' +
                ", geoHeight='" + geoHeight + '\'' +
                '}';
    }
}
