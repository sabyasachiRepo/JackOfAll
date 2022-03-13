package com.tools.core.network;

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    public static RetrofitProvider INSTANCE;

    public static RetrofitProvider getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitProvider();
        }
        return INSTANCE;
    }



    public Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://donate-env.eba-mxxrxi3v.us-east-2.elasticbeanstalk.com")
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }


    @NotNull
    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


}
