package com.glima.getninjas.view.model;

import android.databinding.BaseObservable;

import com.glima.getninjas.model.Coordinates;

/**
 * Created by gustavo on 20/07/16.
 */
public class DetailActivityViewModel extends BaseObservable {

    Coordinates coordinates;

    public DetailActivityViewModel(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getLatitude() {
        return coordinates.getLatitude();
    }

    public String getLongitude() {
        return coordinates.getLongitude();
    }
}
