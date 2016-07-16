package com.glima.getninjas.network.client;

import com.glima.getninjas.model.Lead;
import com.glima.getninjas.model.Offer;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by gustavo on 16/07/16.
 */
public interface GetNinjasClient {

    @GET("leads")
    Observable<List<Lead>> listLeads();

    @GET("offers")
    Observable<List<Offer>> listOffers();

}
