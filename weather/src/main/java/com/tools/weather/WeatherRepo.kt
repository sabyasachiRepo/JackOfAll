package com.tools.weather

import com.tools.weather.network.WeatherAPI
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val weatherAPI: WeatherAPI) {
    suspend fun getCurrentWeather(lat:String,lon:String) = weatherAPI.getCurrentWeather(mapOf("lat" to lat, "lon" to lon,"key" to "57d3d692-f3af-465d-b06e-756037e25512"))
}