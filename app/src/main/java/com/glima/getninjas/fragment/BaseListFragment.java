package com.glima.getninjas.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glima.getninjas.R;
import com.glima.getninjas.activity.DetailActivity;
import com.glima.getninjas.adapter.JobAdapter;
import com.glima.getninjas.databinding.FragmentListBinding;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.network.datasource.JobsDataSource;
import com.glima.getninjas.network.datasource.JobsDataSourceImpl;
import com.glima.getninjas.view.decoration.MargingDecoration;
import com.glima.getninjas.view.model.JobOfferCardViewModel;

import java.util.List;

import rx.Observer;
import rx.functions.Action1;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

/**
 * Created by gustavo on 16/07/16.
 */
public abstract class BaseListFragment extends Fragment implements TitledFragment, Observer<List<Job>>, SwipeRefreshLayout.OnRefreshListener {

    protected FragmentListBinding viewDataBinding;

    protected JobsDataSource jobsDataSource;
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jobsDataSource = new JobsDataSourceImpl(getActivity());

        recyclerView = viewDataBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        recyclerView.addItemDecoration(new MargingDecoration(8));
        recyclerView.setAdapter(new JobAdapter(getContext()));

        ((JobAdapter) recyclerView.getAdapter()).getObservable()
                .subscribe(new Action1<JobOfferCardViewModel>() {
                    @Override
                    public void call(JobOfferCardViewModel viewModel) {
                        startActivity(DetailActivity.newActivity(getContext(), viewModel.getId()));
                    }
                });

        swipeRefreshLayout = viewDataBinding.refresh;
        swipeRefreshLayout.setOnRefreshListener(this);

        loadItems();
    }

    @Override
    public void onRefresh() {
        loadItems();
    }

    protected abstract void loadItems();
}
