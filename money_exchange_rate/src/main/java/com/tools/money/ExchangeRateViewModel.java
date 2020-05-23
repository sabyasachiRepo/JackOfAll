package com.tools.money;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.github.leonardoxh.livedatacalladapter.Resource;
import com.google.gson.JsonArray;
import com.tools.money.network.CurrencyListResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeRateViewModel extends ViewModel {

    MediatorLiveData<List<String>> currencies = new MediatorLiveData<>();
    private ExchangeRateRepo exchangeRateRepo = new ExchangeRateRepo();
    MediatorLiveData<List<String>> convertRate = new MediatorLiveData<>();

    public ExchangeRateRepo getExchangeRateRepo() {
        return exchangeRateRepo;
    }

    public LiveData<List<String>> getCurrencies() {

        currencies.addSource(exchangeRateRepo.getCurrencyList(), currencyListResponseResource -> {
            if (currencyListResponseResource.isSuccess()) {
                currencies.setValue(new ArrayList<>(currencyListResponseResource.getResource().getCurrencies().keySet()));
            }
        });
        return currencies;
    }

    public LiveData<List<String>> getConvertRate(String from, String to, String amount) {
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("format", "json");
        queryParam.put("from", from);
        queryParam.put("to", to);
        queryParam.put("amount", amount);
        convertRate.addSource(exchangeRateRepo.getConvertRate(queryParam), new Observer<Resource<CurrencyListResponse>>() {
            @Override
            public void onChanged(Resource<CurrencyListResponse> currencyListResponseResource) {
                convertRate.setValue(new ArrayList<>());
            }
        });
        return convertRate;
    }


}
