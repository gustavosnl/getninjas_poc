package com.glima.getninjas.view.model;

import android.databinding.BaseObservable;

import com.glima.getninjas.model.Job;

import java.math.BigDecimal;

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

    public String getUserEmail() {
        return job.getRequestingUser().getEmail();
    }

    public String getUserPhone() {
        return job.getRequestingUser().getPhones().get(0);
    }

    public String getRequestingLocal() {
        return job.getRequestingAddress().getFormattedAddress();
    }

    public Double getLatitude() {
        return job.getRequestingAddress().getCoordinates().getLatitude();
    }

    public Double getLongitude() {
        return job.getRequestingAddress().getCoordinates().getLongitude();
    }

    public boolean getIsOffer() {
        return job.isOffer();
    }

    public String getLeadId() {
        return job.getLeadId();
    }

    public String getFormattedDistance() {
        StringBuilder sb = new StringBuilder("a aproximadamente ");

        if (job.getDistanceInKm().compareTo(new BigDecimal(1)) >= 0) {
            sb.append(job.getDistanceInKm()).append(" Km de você");
        } else {
            sb.append(job.getRequestingDistance()).append(" metros de você");
        }
        return sb.toString();
    }

    public String getRawPhone() {
        return job.getRawPhone();
    }
}
