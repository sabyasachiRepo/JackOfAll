package com.tools.money.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface CurrencyAPI {
    @GET("list")
    @Headers("x-rapidapi-host:currency-converter5.p.rapidapi.com", "x-rapidapi-key:xqHhD8Viw0mshdfXqdmyYwkpirccp1ZMhPejsnT7iBCSqthqK8", "format:json")
    suspend fun getCurrencyList(): CurrencyListResponse

    @Headers("x-rapidapi-host:currency-converter5.p.rapidapi.com", "x-rapidapi-key:xqHhD8Viw0mshdfXqdmyYwkpirccp1ZMhPejsnT7iBCSqthqK8", "format:json")
    @GET("convert")
    suspend fun getConvertRate(@QueryMap queryParams: Map<String, String>): ConvertRateResponse
}