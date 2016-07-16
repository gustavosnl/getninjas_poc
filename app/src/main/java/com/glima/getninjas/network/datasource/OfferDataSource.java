package com.glima.getninjas.network.datasource;

import com.glima.getninjas.model.Offer;

import java.util.List;

import rx.Observable;

/**
 * Created by gustavo on 16/07/16.
 */
public interface OfferDataSource {

    Observable<List<Offer>> getOffers();

    Observable<Offer> getOfferInfo(String offerId);
}
