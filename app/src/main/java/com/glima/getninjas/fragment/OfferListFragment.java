package com.glima.getninjas.fragment;

import android.widget.Toast;

import com.glima.getninjas.R;
import com.glima.getninjas.adapter.JobAdapter;
import com.glima.getninjas.model.Job;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gustavo on 16/07/16.
 */
public class OfferListFragment extends BaseListFragment implements Observer<List<Job>> {


    @Override
    public int getTitle() {
        return R.string.title_fragment_offers;
    }

    @Override
    public void onCompleted() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(getContext(), R.string.error_load_offers, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(List<Job> jobs) {
        ((JobAdapter) recyclerView.getAdapter()).reload(jobs);
    }

    protected void loadItems() {
        jobsDataSource.getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
}
