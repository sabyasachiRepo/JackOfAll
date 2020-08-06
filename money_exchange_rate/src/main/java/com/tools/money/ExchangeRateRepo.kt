package com.tools.money

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.tools.core.data.shared.PreferenceStorage
import com.tools.core.network.RetrofitProvider
import com.tools.money.network.ConvertRateResponse
import com.tools.money.network.CurrencyAPI
import com.tools.money.network.CurrencyListResponse
import javax.inject.Inject

class ExchangeRateRepo @Inject constructor(private val currencyAPI: CurrencyAPI, private val preferenceStorage: PreferenceStorage) {
    val currencyList: LiveData<Resource<CurrencyListResponse>>
        get() = currencyAPI.currencyList

    fun getConvertRate(query: Map<String, String>?): LiveData<Resource<ConvertRateResponse>> {
        return RetrofitProvider.getINSTANCE().moneyExchangeRetrofit.create(CurrencyAPI::class.java).getConvertRate(query)
    }

    var lastFromCurrency = preferenceStorage.fromCurrency
        set(value) {
            preferenceStorage.fromCurrency = value
            field = value
        }


    var lastToCurrency = preferenceStorage.toCurrency
        set(value) {
            preferenceStorage.toCurrency = value
            field = value
        }
}