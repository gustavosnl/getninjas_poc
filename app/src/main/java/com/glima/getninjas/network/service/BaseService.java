package com.glima.getninjas.network.service;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by gustavo on 16/07/16.
 */
public class BaseService {

    protected Retrofit retrofit;
    private Context context;

    public BaseService(Context context, int baseUrlResourceId) {
        this.context = context;

        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(baseUrlResourceId))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(setupClient())
                .build();
    }

    private OkHttpClient setupClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return httpClient.build();
    }

}
