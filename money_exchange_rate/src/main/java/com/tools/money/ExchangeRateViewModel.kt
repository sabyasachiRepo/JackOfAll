package com.tools.money

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import com.tools.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ExchangeRateViewModel @Inject constructor(private val exchangeRateRepo: ExchangeRateRepo) : BaseViewModel() {
    val convertRate = MediatorLiveData<String>()

    fun getCurrencies() = liveData(Dispatchers.IO) {
        emit(com.tools.core.network.Resource.loading(data = null))
        try {
            emit(com.tools.core.network.Resource.success(data = exchangeRateRepo.getCurrencyList()))
        } catch (exception: Exception) {
            emit(com.tools.core.network.Resource.error(data = null, message = exception.message
                    ?: "Error Occurred!"))
        }

    }

    fun getConvertRate(amountToConvert: String) {
        convertRate.addSource(convert(amountToConvert)) { convertRateResponseResource ->
            val result = StringBuilder()
            val convertRateResponse = convertRateResponseResource.data
            convertRateResponse?.apply {
                result.append(amount)
                result.append(" ")
                result.append(baseCurrencyCode)
                result.append(" = ")
                result.append(rates[toCurrency]?.rateForAmount)
                result.append(" ")
                result.append(toCurrency)
                convertRate.value = result.toString()
            }


        }
    }

    private fun convert(amount: String) = liveData(Dispatchers.IO) {
        val queryParam = mapOf("format" to "json", "from" to fromCurrency, "to" to toCurrency, "amount" to amount)
        emit(com.tools.core.network.Resource.loading(data = null))
        try {
            emit(com.tools.core.network.Resource.success(data = exchangeRateRepo.getConvertRate(queryParam)))
        } catch (exception: Exception) {
            emit(com.tools.core.network.Resource.error(data = null, message = exception.message
                    ?: "Error Occurred!"))
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