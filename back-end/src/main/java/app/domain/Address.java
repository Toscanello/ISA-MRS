package app.domain;

public class Address {
    private String street;
    private String streetNumber;
    private String place;
    private String country;
    private Location location;

    public Address() {
    }

    public Address(String street, String streetNumber, String place, String country, Location location) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.place = place;
        this.country = country;
        this.location = location;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", place='" + place + '\'' +
                ", country='" + country + '\'' +
                ", location=" + location +
                '}';
    }
}
