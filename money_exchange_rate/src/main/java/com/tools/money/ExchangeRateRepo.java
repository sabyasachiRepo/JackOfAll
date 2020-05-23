package com.tools.money;


import androidx.lifecycle.LiveData;

import com.github.leonardoxh.livedatacalladapter.Resource;
import com.tools.core.network.RetrofitProvider;
import com.tools.money.network.CurrencyAPI;
import com.tools.money.network.CurrencyListResponse;

import java.util.Map;

import retrofit2.Response;

public class ExchangeRateRepo {

    LiveData<Resource<CurrencyListResponse>> getCurrencyList() {
        return RetrofitProvider.getINSTANCE().getRetrofit().create(CurrencyAPI.class).getCurrencyList();
    }

    LiveData<Resource<CurrencyListResponse>> getConvertRate(Map query) {
        return RetrofitProvider.getINSTANCE().getRetrofit().create(CurrencyAPI.class).getConvertRate(query);
    }
}
