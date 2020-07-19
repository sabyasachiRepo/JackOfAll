package com.tools.money;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class ViewModelFactory implements ViewModelProvider.Factory {

    ExchangeRateRepo exchangeRateRepo;

    public ViewModelFactory(ExchangeRateRepo exchangeRateRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ExchangeRateViewModel(exchangeRateRepo);

    }
}
