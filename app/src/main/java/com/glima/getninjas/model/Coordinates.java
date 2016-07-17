package com.glima.getninjas.model;

/**
 * Created by gustavo on 17/07/16.
 */
public class Coordinates {

    private Double latitude;
    private Double longitude;

    public Coordinates(String latitude, String longitude) {
        this.latitude = Double.valueOf(latitude);
        this.longitude = Double.valueOf(longitude);
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
