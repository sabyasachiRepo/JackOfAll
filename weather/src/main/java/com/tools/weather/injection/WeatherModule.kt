package com.tools.news.injection

import com.tools.core.network.RetrofitProvider
import com.tools.weather.ViewModelFactory
import com.tools.weather.WeatherRepo
import com.tools.weather.network.WeatherAPI
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {
    @Provides
    fun provideWeatherAPI(): WeatherAPI {
        return RetrofitProvider.getINSTANCE().retrofit.create(WeatherAPI::class.java)
    }

    @Provides
    fun provideViewModelFactory(weatherRepo: WeatherRepo?): ViewModelFactory {
        return ViewModelFactory(weatherRepo!!)
    }
}