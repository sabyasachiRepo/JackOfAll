package com.tools.weather.network

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherAPI {

    @GET("nearest_city")
    suspend fun getCurrentWeather(@QueryMap queryParams: Map<String, String>): WeatherResponse


}