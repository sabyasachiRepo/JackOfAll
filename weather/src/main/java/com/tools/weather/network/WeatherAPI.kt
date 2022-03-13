package com.tools.weather.network

import com.tools.core.network.header.AuthenticatedHeaders
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface WeatherAPI {

    @GET("/other/api/current-weather")
    suspend fun getCurrentWeather(
        @HeaderMap authenticatedHeaders: AuthenticatedHeaders,
        @QueryMap queryParams: Map<String, String>
    ): WeatherResponse


}