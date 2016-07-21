package com.glima.getninjas.network.client;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by gustavo on 16/07/16.
 */
public interface GetNinjasClient {

    @GET("{job_kind}")
    Observable<Response<String>> list(@Path("job_kind") String jobKind);

    @GET("{job_id}")
    Observable<Response<String>> getInfo(@Path("job_id") String id);

}
