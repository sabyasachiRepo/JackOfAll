package com.tools.money

import com.tools.core.data.shared.PreferenceStorage
import com.tools.money.network.CurrencyAPI
import javax.inject.Inject

class ExchangeRateRepo @Inject constructor(private val currencyAPI: CurrencyAPI, private val preferenceStorage: PreferenceStorage) {

    suspend fun getCurrencyList() = currencyAPI.getCurrencyList()

    suspend fun getConvertRate(query: Map<String, String>) = currencyAPI.getConvertRate(query)

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