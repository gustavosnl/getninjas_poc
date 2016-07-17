package com.glima.getninjas.fragment;

import android.os.Bundle;

import com.glima.getninjas.R;
import com.glima.getninjas.model.Offer;
import com.glima.getninjas.network.datasource.OfferDataSource;
import com.glima.getninjas.network.datasource.OfferDataSourceImpl;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by gustavo on 16/07/16.
 */
public class OfferListFragment extends BaseFragment implements Observer<List<Offer>> {

    private OfferDataSource offerDataSource;

    @Override
    protected int getLayout() {
        return R.layout.fragment_offers;
    }

    @Override
    public int getTitle() {
        return R.string.title_fragment_offers;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        offerDataSource = new OfferDataSourceImpl(getActivity());
        offerDataSource.getOffers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(List<Offer> offers) {

    }
}
