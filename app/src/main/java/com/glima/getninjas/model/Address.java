package com.glima.getninjas.model;

/**
 * Created by gustavo on 17/07/16.
 */
public class Address {

    private String city;
    private String neighborhood;
    private String state;
    private Coordinates coordinates;

    public Address(String city, String neighborhood, String state) {
        this.city = city;
        this.neighborhood = neighborhood;
        this.state = state;
    }

    public Address(String city, String neighborhood, String state, Coordinates coordinates) {
        this(city, neighborhood, state);
        this.coordinates = coordinates;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getState() {
        return state;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getFormattedAddress() {
        return new StringBuilder()
                .append(neighborhood)
                .append(" - ")
                .append(city).toString();
    }
}
