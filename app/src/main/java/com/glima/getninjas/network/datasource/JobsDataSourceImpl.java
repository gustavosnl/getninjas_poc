package com.glima.getninjas.network.datasource;

import android.content.Context;

import com.glima.getninjas.R;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.network.client.GetNinjasClient;
import com.glima.getninjas.network.parser.JobListParser;
import com.glima.getninjas.network.parser.SingleJobParser;
import com.glima.getninjas.network.service.BaseService;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by gustavo on 16/07/16.
 */
public class JobsDataSourceImpl extends BaseService implements JobsDataSource {

    private GetNinjasClient client;
    private JobListParser listParser;
    private SingleJobParser jobParser;

    public JobsDataSourceImpl(Context context) {
        super(context, R.string.api_getninjas);
        client = retrofit.create(GetNinjasClient.class);
        listParser = new JobListParser();
        jobParser = new SingleJobParser();
    }

    @Override
    public Observable<List<Job>> listJobs(String jobKind) {
        return client.list(jobKind)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Response<String>, List<Job>>() {
                    @Override
                    public List<Job> call(Response<String> response) {
                        try {
                            return listParser.parse(new ByteArrayInputStream(response.body().getBytes()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            return new ArrayList<>();
                        }
                    }
                });
    }

    @Override
    public Observable<Job> getInfo(String offerId) {
        return client.getInfo(offerId)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Response<String>, Job>() {
                    @Override
                    public Job call(Response<String> response) {
                        try {
                            return jobParser.parse(new ByteArrayInputStream(response.body().getBytes()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                });
    }
}
