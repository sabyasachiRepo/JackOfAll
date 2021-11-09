package com.tools.core.network;

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory;
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory;

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

    public Retrofit getMoneyExchangeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://currency-converter5.p.rapidapi.com/currency/")
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }

    public Retrofit getNewsRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }

    public Retrofit getWeatherRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.airvisual.com/v2/")
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }

    public Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://donate-env.eba-mxxrxi3v.us-east-2.elasticbeanstalk.com/donate/api/v1/")
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
