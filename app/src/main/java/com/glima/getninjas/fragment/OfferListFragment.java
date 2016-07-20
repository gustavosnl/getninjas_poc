package com.glima.getninjas.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.glima.getninjas.R;
import com.glima.getninjas.activity.DetailActivity;
import com.glima.getninjas.adapter.OfferAdapter;
import com.glima.getninjas.databinding.FragmentOffersBinding;
import com.glima.getninjas.model.Offer;
import com.glima.getninjas.network.datasource.JobsDataSourceImpl;
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
public class OfferListFragment extends BaseListFragment implements Observer<List<Offer>> {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jobsDataSource = new JobsDataSourceImpl(getActivity());

        recyclerView = ((FragmentOffersBinding) viewDataBinding).recyclerView;
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

        swipeRefreshLayout = ((FragmentOffersBinding) viewDataBinding).refresh;
        swipeRefreshLayout.setOnRefreshListener(this);

        loadItems();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_offers;
    }

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
    public void onNext(List<Offer> offers) {
        ((OfferAdapter) recyclerView.getAdapter()).reload(offers);
    }

    protected void loadItems() {
        jobsDataSource.getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
}
