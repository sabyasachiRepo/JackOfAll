package com.tools.money

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.leonardoxh.livedatacalladapter.Resource
import com.tools.money.network.ConvertRateResponse
import com.tools.money.network.CurrencyListResponse
import java.util.*
import javax.inject.Inject

class ExchangeRateViewModel @Inject constructor(private val exchangeRateRepo: ExchangeRateRepo) : ViewModel() {
    private val currencies = MediatorLiveData<List<String>>()
    val convertRate = MediatorLiveData<String>()
    fun getCurrencies(): LiveData<List<String>> {
        currencies.addSource(exchangeRateRepo.currencyList) { currencyListResponseResource: Resource<CurrencyListResponse> ->
            if (currencyListResponseResource.isSuccess) {
                currencies.value = ArrayList(currencyListResponseResource.resource!!.currencies.keys)
            }
        }
        return currencies
    }

    fun getConvertRate(fromCurrency: String, toCurrency: String, amount: String): LiveData<String> {
        val result = StringBuilder()
        val queryParam = mapOf("format" to "json", "from" to fromCurrency, "to" to toCurrency, "amount" to amount)

        convertRate.addSource(exchangeRateRepo.getConvertRate(queryParam), Observer<Resource<ConvertRateResponse>> { convertRateResponseResource ->
            if (convertRateResponseResource.isSuccess) {
                val convertRateResponse = convertRateResponseResource.resource
                result.append(convertRateResponse!!.amount)
                result.append(" ")
                result.append(convertRateResponse.baseCurrencyCode)
                result.append(" = ")
                result.append(convertRateResponse.rates[toCurrency]!!.rateForAmount)
                result.append(" ")
                result.append(toCurrency)
                convertRate.value = result.toString()
            }
        })
        return convertRate
    }

    fun getConvertRateNew(amount: String): Unit {
        val result = StringBuilder()
        val queryParam = mapOf("format" to "json", "from" to fromCurrency, "to" to toCurrency, "amount" to amount)

        convertRate.addSource(exchangeRateRepo.getConvertRate(queryParam)) { convertRateResponseResource ->
            if (convertRateResponseResource.isSuccess) {
                val convertRateResponse = convertRateResponseResource.resource
                result.append(convertRateResponse!!.amount)
                result.append(" ")
                result.append(convertRateResponse.baseCurrencyCode)
                result.append(" = ")
                result.append(convertRateResponse.rates[toCurrency]!!.rateForAmount)
                result.append(" ")
                result.append(toCurrency)
                convertRate.value = result.toString()
            }
        }
    }

    var fromCurrency = exchangeRateRepo.lastFromCurrency
        set(value) {
            exchangeRateRepo.lastFromCurrency = value
            field = value
        }

    var toCurrency = exchangeRateRepo.lastToCurrency
        set(value) {
            exchangeRateRepo.lastToCurrency = value
            field = value
        }

}