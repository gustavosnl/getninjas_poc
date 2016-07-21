package com.glima.getninjas.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.glima.getninjas.R;
import com.glima.getninjas.databinding.ActivityDetailBinding;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.network.datasource.JobsDataSource;
import com.glima.getninjas.network.datasource.JobsDataSourceImpl;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by gustavo on 19/07/16.
 */
public class DetailActivity extends BaseActivity implements Observer<Job> {
    public static final String JOB_ID = "jobId";

    private JobsDataSource jobsDataSource;

    public static Intent newActivity(Context originContext, String id) {
        Intent intent = new Intent(originContext, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(JOB_ID, id);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jobsDataSource = new JobsDataSourceImpl(this);
        jobsDataSource.getLeadInfo(getIntent().getStringExtra(JOB_ID))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = ((ActivityDetailBinding) viewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Job job) {

    }
}
