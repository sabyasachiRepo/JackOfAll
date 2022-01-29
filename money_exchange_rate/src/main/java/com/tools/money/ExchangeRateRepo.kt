package com.tools.money

import com.tools.core.data.shared.PreferenceStorage
import com.tools.core.network.header.ApiHeadersProvider
import com.tools.money.network.CurrencyAPI
import javax.inject.Inject

class ExchangeRateRepo @Inject constructor(private val currencyAPI: CurrencyAPI, private val preferenceStorage: PreferenceStorage,private val apiMainHeadersProvider: ApiHeadersProvider) {

    suspend fun getCurrencyList() = currencyAPI.getCurrencyList(apiMainHeadersProvider.getAuthenticatedHeaders(preferenceStorage.accessToken))

    suspend fun getConvertRate(query: Map<String, String>) = currencyAPI.getConvertRate(apiMainHeadersProvider.getAuthenticatedHeaders(preferenceStorage.accessToken),query)

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