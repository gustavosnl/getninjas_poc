package com.glima.getninjas.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.glima.getninjas.R;
import com.glima.getninjas.adapter.OfferAdapter;
import com.glima.getninjas.databinding.FragmentOffersBinding;
import com.glima.getninjas.model.Offer;
import com.glima.getninjas.network.datasource.OfferDataSource;
import com.glima.getninjas.network.datasource.OfferDataSourceImpl;
import com.glima.getninjas.ui.decoration.MargingDecoration;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

/**
 * Created by gustavo on 16/07/16.
 */
public class OfferListFragment extends BaseFragment implements Observer<List<Offer>>, SwipeRefreshLayout.OnRefreshListener {

    private OfferDataSource offerDataSource;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayout() {
        return R.layout.fragment_offers;
    }

    @Override
    public int getTitle() {
        return R.string.title_fragment_offers;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        offerDataSource = new OfferDataSourceImpl(getActivity());

        recyclerView = ((FragmentOffersBinding) viewDataBinding).recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        recyclerView.addItemDecoration(new MargingDecoration(8));
        recyclerView.setAdapter(new OfferAdapter(getActivity()));

        swipeRefreshLayout = ((FragmentOffersBinding) viewDataBinding).refresh;
        swipeRefreshLayout.setOnRefreshListener(this);

        loadItems();
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

    @Override
    public void onRefresh() {
        loadItems();
    }

    private void loadItems() {
        offerDataSource.getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
}
