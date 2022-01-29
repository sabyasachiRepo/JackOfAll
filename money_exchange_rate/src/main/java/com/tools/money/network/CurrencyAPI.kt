package com.tools.money.network

import com.tools.core.network.header.AuthenticatedHeaders
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface CurrencyAPI {
    @GET("/other/api/currencies/list")
    suspend fun getCurrencyList(@HeaderMap authenticatedHeaders: AuthenticatedHeaders): CurrencyListResponse

    @GET("/other/api/currencies/convert")
    suspend fun getConvertRate(@HeaderMap authenticatedHeaders: AuthenticatedHeaders, @QueryMap queryParams: Map<String, String>): ConvertRateResponse
}