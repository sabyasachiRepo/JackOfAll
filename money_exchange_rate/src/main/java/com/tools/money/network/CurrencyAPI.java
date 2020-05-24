package com.tools.money.network;

import androidx.lifecycle.LiveData;

import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface CurrencyAPI {

    @Headers({
            "x-rapidapi-host:currency-converter5.p.rapidapi.com",
            "x-rapidapi-key:xqHhD8Viw0mshdfXqdmyYwkpirccp1ZMhPejsnT7iBCSqthqK8",
            "format:json"})
    @GET("list")
    LiveData<Resource<CurrencyListResponse>> getCurrencyList();

    @Headers({
            "x-rapidapi-host:currency-converter5.p.rapidapi.com",
            "x-rapidapi-key:xqHhD8Viw0mshdfXqdmyYwkpirccp1ZMhPejsnT7iBCSqthqK8",
            "format:json"})
    @GET("convert")
    LiveData<Resource<ConvertRateResponse>> getConvertRate(@QueryMap Map<String, String> queryParams);

}
