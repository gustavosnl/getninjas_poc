package com.glima.getninjas.fragment;

import android.widget.Toast;

import com.glima.getninjas.R;
import com.glima.getninjas.adapter.JobAdapter;
import com.glima.getninjas.model.Job;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gustavo on 16/07/16.
 */
public class LeadListFragment extends BaseListFragment {

    @Override
    public int getTitle() {
        return R.string.title_fragment_leads;
    }

    @Override
    public void onCompleted() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(getContext(), R.string.error_load_leads, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(List<Job> leads) {
        ((JobAdapter) recyclerView.getAdapter()).reload(leads);
    }

    @Override
    protected void loadItems() {
        jobsDataSource.listJobs("leads")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
}
