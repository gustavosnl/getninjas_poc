package com.glima.getninjas.network.datasource;

import com.glima.getninjas.model.Job;

import java.util.List;

import rx.Observable;

/**
 * Created by gustavo on 16/07/16.
 */
public interface JobsDataSource {

    Observable<List<Job>> listJobs(String jobKind);

    Observable<Job> getInfo(String offerId);

}
