package com.tools.money;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.github.leonardoxh.livedatacalladapter.Resource;
import com.tools.money.network.ConvertRateResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class ExchangeRateViewModel extends ViewModel {

    private MediatorLiveData<List<String>> currencies = new MediatorLiveData<>();
    private ExchangeRateRepo exchangeRateRepo;
    private MediatorLiveData<String> convertRate = new MediatorLiveData<>();

    @Inject
    public ExchangeRateViewModel(ExchangeRateRepo exchangeRateRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
    }

    public LiveData<List<String>> getCurrencies() {

        currencies.addSource(exchangeRateRepo.getCurrencyList(), currencyListResponseResource -> {
            if (currencyListResponseResource.isSuccess()) {
                currencies.setValue(new ArrayList<>(currencyListResponseResource.getResource().getCurrencies().keySet()));
            }
        });
        return currencies;
    }

    public LiveData<String> getConvertRate(String fromCurrency, String toCurrency, String amount) {
        StringBuilder result = new StringBuilder();
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("format", "json");
        queryParam.put("from", fromCurrency);
        queryParam.put("to", toCurrency);
        queryParam.put("amount", amount);
        convertRate.addSource(exchangeRateRepo.getConvertRate(queryParam), new Observer<Resource<ConvertRateResponse>>() {
            @Override
            public void onChanged(Resource<ConvertRateResponse> convertRateResponseResource) {
                if (convertRateResponseResource.isSuccess()) {
                    ConvertRateResponse convertRateResponse = convertRateResponseResource.getResource();
                    result.append(convertRateResponse.getAmount());
                    result.append(" ");
                    result.append(convertRateResponse.getBaseCurrencyCode());
                    result.append(" = ");
                    result.append(convertRateResponse.getRates().get(toCurrency).getRateForAmount());
                    result.append(" ");
                    result.append(toCurrency);
                    convertRate.setValue(result.toString());
                }
            }
        });
        return convertRate;
    }


}
