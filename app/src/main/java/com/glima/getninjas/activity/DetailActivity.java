package com.glima.getninjas.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.glima.getninjas.R;
import com.glima.getninjas.adapter.InfoAdapter;
import com.glima.getninjas.databinding.ActivityDetailBinding;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.network.datasource.JobsDataSource;
import com.glima.getninjas.network.datasource.JobsDataSourceImpl;
import com.glima.getninjas.view.decoration.MargingDecoration;
import com.glima.getninjas.view.model.DetailActivityViewModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

/**
 * Created by gustavo on 19/07/16.
 */
public class DetailActivity extends BaseActivity implements Observer<Job> {
    public static final String JOB_ID = "jobId";

    private JobsDataSource jobsDataSource;
    private DetailActivityViewModel viewModel;
    private RecyclerView recyclerView;

    public static Intent newActivity(Context originContext, String id) {
        Intent intent = new Intent(originContext, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(JOB_ID, id);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void init() {
        jobsDataSource = new JobsDataSourceImpl(this);
        jobsDataSource.getInfo(getIntent().getStringExtra(JOB_ID))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);

        recyclerView = ((ActivityDetailBinding) viewDataBinding).adtionalInfoRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        recyclerView.addItemDecoration(new MargingDecoration(8));
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
        viewModel = new DetailActivityViewModel(job);
        ((ActivityDetailBinding) viewDataBinding).setJob(viewModel);
        recyclerView.setAdapter(new InfoAdapter(this, job.getJobInfo()));
    }
}
