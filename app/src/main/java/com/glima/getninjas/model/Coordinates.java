package com.glima.getninjas.model;

/**
 * Created by gustavo on 17/07/16.
 */
public class Coordinates {

    private Double latitude;
    private Double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude =latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
