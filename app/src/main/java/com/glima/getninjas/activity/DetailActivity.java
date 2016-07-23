package com.glima.getninjas.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.glima.getninjas.R;
import com.glima.getninjas.adapter.InfoAdapter;
import com.glima.getninjas.databinding.ActivityDetailBinding;
import com.glima.getninjas.model.Coordinates;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.network.datasource.JobsDataSource;
import com.glima.getninjas.network.datasource.JobsDataSourceImpl;
import com.glima.getninjas.view.decoration.MargingDecoration;
import com.glima.getninjas.view.model.DetailActivityViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static android.view.View.GONE;

/**
 * Created by gustavo on 19/07/16.
 */
public class DetailActivity extends BaseActivity implements Observer<Job>, OnMapReadyCallback {
    public static final String JOB_ID = "jobId";

    private JobsDataSource jobsDataSource;
    private PublishSubject<GoogleMap> mapPublishSubject = PublishSubject.create();
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

        recyclerView = ((ActivityDetailBinding) viewDataBinding).adtionalInfoRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        recyclerView.addItemDecoration(new MargingDecoration(8));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Observable.zip(
                load(getIntent().getStringExtra(JOB_ID)),
                mapPublishSubject.asObservable(),
                new Func2<Job, GoogleMap, Job>() {
                    @Override
                    public Job call(Job job, GoogleMap map) {
                        Coordinates coordinates = job.getRequestingAddress().getCoordinates();
                        LatLng userLocation = new LatLng(coordinates.getLatitude(), coordinates.getLongitude());

                        map.moveCamera(getCameraPosition(userLocation));
                        map.addCircle(getCircle(userLocation));
                        return job;
                    }
                })
                .compose(this.<Job>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
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
        ((ActivityDetailBinding) viewDataBinding).progressBar.setVisibility(GONE);
        ((ActivityDetailBinding) viewDataBinding).content.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("ERROR: ", e.getMessage());
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(Job job) {
        viewModel = new DetailActivityViewModel(job);
        getSupportActionBar().setTitle(viewModel.getTitle());
        ((ActivityDetailBinding) viewDataBinding).setJob(viewModel);
        recyclerView.setAdapter(new InfoAdapter(this, job.getJobInfo()));
        viewDataBinding.notifyChange();
    }

    protected Observable<Job> load(String id) {
        return jobsDataSource.getInfo(id)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void deferOfferAction(View view) {
        finish();
    }

    public void acceptOfferAction(View view) {
        load(viewModel.getLeadId()).subscribe(this);
    }

    public void startDialer(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + viewModel.getRawPhone()));
        startActivity(callIntent);
    }

    public void startWhatsapp(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.default_whatsapp_message));
        sendIntent.setType("text/plain");
        sendIntent.setPackage(getString(R.string.whatsapp_package_name));
        startActivity(sendIntent);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapPublishSubject.onNext(map);
    }

    @NonNull
    private CameraUpdate getCameraPosition(LatLng userLocation) {
        CameraPosition tartget = CameraPosition.builder().target(userLocation).zoom(13).build();
        return CameraUpdateFactory.newCameraPosition(tartget);
    }

    @NonNull
    private CircleOptions getCircle(LatLng userLocation) {
        return new CircleOptions()
                .center(userLocation)
                .radius(1000)
                .strokeColor(ContextCompat.getColor(this, R.color.circle_map_border))
                .fillColor(ContextCompat.getColor(this, R.color.circle_map_inside));
    }

}
