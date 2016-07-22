package com.glima.getninjas.view.model;

import android.databinding.BaseObservable;

import com.glima.getninjas.model.Job;

/**
 * Created by gustavo on 20/07/16.
 */
public class DetailActivityViewModel extends BaseObservable {

    private Job job;

    public DetailActivityViewModel(Job job) {
        this.job = job;
    }

    public String getTitle() {
        return job.getTitle();
    }

    public String getUserName() {
        return job.getRequestingUser().getName();
    }

    public String getRequestingLocal() {
        return job.getRequestingAddress().getFormattedAddress();
    }

    public String getLatitude() {
        return job.getRequestingAddress().getCoordinates().getLatitude();
    }

    public String getLongitude() {
        return job.getRequestingAddress().getCoordinates().getLongitude();
    }
}
