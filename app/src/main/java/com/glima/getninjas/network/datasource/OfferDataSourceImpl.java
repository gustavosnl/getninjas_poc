package com.glima.getninjas.network.datasource;

import android.content.Context;

import com.glima.getninjas.R;
import com.glima.getninjas.model.Offer;
import com.glima.getninjas.network.client.GetNinjasClient;
import com.glima.getninjas.network.service.BaseService;

import java.util.List;

import rx.Observable;

/**
 * Created by gustavo on 16/07/16.
 */
public class OfferDataSourceImpl extends BaseService implements OfferDataSource{

    private GetNinjasClient client;

    public OfferDataSourceImpl(Context context) {
        super(context, R.string.api_getninjas);
        client = retrofit.create(GetNinjasClient.class);
    }

    @Override
    public Observable<List<Offer>> getOffers() {
        return null;
    }

    @Override
    public Observable<Offer> getOfferInfo(String offerId) {
        return null;
    }
}
