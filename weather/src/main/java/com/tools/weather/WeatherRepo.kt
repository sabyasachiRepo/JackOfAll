package com.tools.weather

import com.tools.core.data.shared.PreferenceStorage
import com.tools.core.network.header.ApiHeadersProvider
import com.tools.weather.network.WeatherAPI
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherAPI: WeatherAPI,
    private val preferenceStorage: PreferenceStorage,
    private val apiMainHeadersProvider: ApiHeadersProvider
) {
    suspend fun getCurrentWeather(lat: String, lon: String) = weatherAPI.getCurrentWeather(
        apiMainHeadersProvider.getAuthenticatedHeaders(preferenceStorage.accessToken),
        mapOf("lat" to lat, "lon" to lon)
    )
}