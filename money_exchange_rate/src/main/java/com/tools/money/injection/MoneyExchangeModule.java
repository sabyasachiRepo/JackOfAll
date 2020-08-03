package com.tools.money.injection;


import com.tools.core.network.RetrofitProvider;
import com.tools.money.ExchangeRateRepo;
import com.tools.money.ViewModelFactory;
import com.tools.money.network.CurrencyAPI;

import dagger.Module;
import dagger.Provides;

@Module
public class MoneyExchangeModule {

    @Provides
     CurrencyAPI provideCurrencyAPI() {
        return RetrofitProvider.getINSTANCE().getMoneyExchangeRetrofit().create(CurrencyAPI.class);
    }

    @Provides
    ViewModelFactory provideViewModelFactory(ExchangeRateRepo exchangeRateRepo){
        return new ViewModelFactory(exchangeRateRepo);
    }

}
