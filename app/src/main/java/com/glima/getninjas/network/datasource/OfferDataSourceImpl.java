package com.glima.getninjas.network.datasource;

import android.content.Context;

import com.glima.getninjas.R;
import com.glima.getninjas.model.Offer;
import com.glima.getninjas.network.client.GetNinjasClient;
import com.glima.getninjas.network.parser.OfferParser;
import com.glima.getninjas.network.service.BaseService;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by gustavo on 16/07/16.
 */
public class OfferDataSourceImpl extends BaseService implements OfferDataSource {

    private GetNinjasClient client;
    private OfferParser parser;

    public OfferDataSourceImpl(Context context) {
        super(context, R.string.api_getninjas);
        client = retrofit.create(GetNinjasClient.class);
        parser = new OfferParser();
    }

    @Override
    public Observable<List<Offer>> getOffers() {
        return client.listOffers()
                .subscribeOn(Schedulers.io())
                .map(new Func1<Response<String>, List<Offer>>() {
                    @Override
                    public List<Offer> call(Response<String> response) {
                        try {
                            return parser.parse(new ByteArrayInputStream(response.body().getBytes()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            return new ArrayList<>();
                        }
                    }
                });
    }

    @Override
    public Observable<Offer> getOfferInfo(String offerId) {
        return null;
    }
}
