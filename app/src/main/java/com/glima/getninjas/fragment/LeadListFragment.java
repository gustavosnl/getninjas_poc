package com.glima.getninjas.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.glima.getninjas.R;
import com.glima.getninjas.activity.DetailActivity;
import com.glima.getninjas.adapter.OfferAdapter;
import com.glima.getninjas.databinding.FragmentLeadsBinding;
import com.glima.getninjas.model.Lead;
import com.glima.getninjas.ui.decoration.MargingDecoration;
import com.glima.getninjas.ui.model.OfferCardViewModel;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

/**
 * Created by gustavo on 16/07/16.
 */
public class LeadListFragment extends BaseListFragment implements Observer<List<Lead>> {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = ((FragmentLeadsBinding) viewDataBinding).recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        recyclerView.addItemDecoration(new MargingDecoration(8));
        recyclerView.setAdapter(new OfferAdapter(getActivity()));

        ((OfferAdapter) recyclerView.getAdapter()).getObservable()
                .subscribe(new Action1<OfferCardViewModel>() {
                    @Override
                    public void call(OfferCardViewModel viewModel) {
                        startActivity(new Intent(getContext(), DetailActivity.class));
                    }
                });

        swipeRefreshLayout = ((FragmentLeadsBinding) viewDataBinding).refresh;
        swipeRefreshLayout.setOnRefreshListener(this);

        loadItems();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_leads;
    }

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
    public void onNext(List<Lead> leads) {

    }

    @Override
    protected void loadItems() {
        jobsDataSource.getLeads()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
}
