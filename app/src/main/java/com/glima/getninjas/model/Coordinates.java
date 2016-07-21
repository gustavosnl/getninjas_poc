package com.glima.getninjas.model;

/**
 * Created by gustavo on 17/07/16.
 */
public class Coordinates {

    private String latitude;
    private String longitude;

    public Coordinates(String latitude, String longitude) {
        this.latitude =latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
