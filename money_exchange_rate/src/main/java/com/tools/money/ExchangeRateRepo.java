package com.tools.money;


import androidx.lifecycle.LiveData;

import com.github.leonardoxh.livedatacalladapter.Resource;
import com.tools.core.network.RetrofitProvider;
import com.tools.money.network.ConvertRateResponse;
import com.tools.money.network.CurrencyAPI;
import com.tools.money.network.CurrencyListResponse;

import java.util.Map;

import javax.inject.Inject;

public class ExchangeRateRepo {

    private CurrencyAPI currencyAPI;

    @Inject
    public ExchangeRateRepo(CurrencyAPI currencyAPI) {
        this.currencyAPI = currencyAPI;
    }

    LiveData<Resource<CurrencyListResponse>> getCurrencyList() {
        return currencyAPI.getCurrencyList();
    }

    LiveData<Resource<ConvertRateResponse>> getConvertRate(Map query) {
        return RetrofitProvider.getINSTANCE().getRetrofit().create(CurrencyAPI.class).getConvertRate(query);
    }
}
