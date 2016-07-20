package com.glima.getninjas.network.datasource;

import com.glima.getninjas.model.Lead;
import com.glima.getninjas.model.Offer;

import java.util.List;

import rx.Observable;

/**
 * Created by gustavo on 16/07/16.
 */
public interface JobsDataSource {

    Observable<List<Offer>> getOffers();

    Observable<List<Lead>> getLeads();


    Observable<Offer> getOfferInfo(String offerId);
}
