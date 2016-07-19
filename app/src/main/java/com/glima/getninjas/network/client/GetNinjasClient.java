package com.glima.getninjas.network.client;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by gustavo on 16/07/16.
 */
public interface GetNinjasClient {

    @GET("leads")
    Observable<Response<String>> listLeads();

    @GET("offers")
    Observable<Response<String>> listOffers();

}
