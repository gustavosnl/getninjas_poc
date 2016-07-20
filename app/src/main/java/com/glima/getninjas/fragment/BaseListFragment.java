package com.glima.getninjas.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glima.getninjas.network.datasource.JobsDataSource;
import com.glima.getninjas.network.datasource.JobsDataSourceImpl;

/**
 * Created by gustavo on 16/07/16.
 */
public abstract class BaseListFragment extends Fragment implements TitledFragment, SwipeRefreshLayout.OnRefreshListener {

    protected ViewDataBinding viewDataBinding;

    protected JobsDataSource jobsDataSource;
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jobsDataSource = new JobsDataSourceImpl(getActivity());
    }

    @Override
    public void onRefresh() {
        loadItems();
    }

    protected abstract int getLayout();

    protected abstract void loadItems();
}
