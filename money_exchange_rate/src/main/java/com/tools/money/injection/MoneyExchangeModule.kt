package com.tools.money.injection

import com.tools.core.network.RetrofitProvider
import com.tools.money.ExchangeRateRepo
import com.tools.money.ViewModelFactory
import com.tools.money.network.CurrencyAPI
import dagger.Module
import dagger.Provides

@Module
class MoneyExchangeModule {
    @Provides
    fun provideCurrencyAPI(): CurrencyAPI {
        return RetrofitProvider.getINSTANCE().moneyExchangeRetrofit.create(CurrencyAPI::class.java)
    }

    @Provides
    fun provideViewModelFactory(exchangeRateRepo: ExchangeRateRepo): ViewModelFactory {
        return ViewModelFactory(exchangeRateRepo)
    }
}