package com.tools.weather

import androidx.lifecycle.liveData
import com.tools.core.BaseViewModel
import com.tools.core.network.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepo: WeatherRepo) : BaseViewModel() {

     fun getCurrentWeather(lat:String,lon:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = weatherRepo.getCurrentWeather(lat,lon)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message
                ?: "Error Occurred!"))
        }
    }


}