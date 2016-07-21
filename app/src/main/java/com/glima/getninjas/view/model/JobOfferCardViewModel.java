package com.glima.getninjas.view.model;

import android.databinding.BaseObservable;

import com.glima.getninjas.model.Job;

import static com.glima.getninjas.model.JobCondition.OFFER;

/**
 * Created by gustavo on 18/07/16.
 */
public class JobOfferCardViewModel extends BaseObservable {

    private Job job;

    public JobOfferCardViewModel(Job job) {
        this.job = job;
    }

    public String getTitle() {
        return job.getTitle();
    }

    public String getUserName() {
        return job.getRequestingUser().getName();
    }

    public String getDate() {
        return job.getCreationDate();
    }

    public String getRequestingLocal() {
        return job.getRequestingAddress().getFormattedAddress();
    }

    public boolean isRead() {
        return job.isRead();
    }

    public boolean isStatusVisible() {
        return OFFER.equals(job.getCondition());
    }

    public String getId() {
        return job.getId();
    }
}
