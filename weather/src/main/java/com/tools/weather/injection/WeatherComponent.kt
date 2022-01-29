package com.tools.news.injection

import com.tools.jackofall.di.FeatureModuleDependency
import com.tools.weather.WeatherFragment
import dagger.Component

@Component(modules = [WeatherModule::class], dependencies = [FeatureModuleDependency::class])
interface WeatherComponent {
    @Component.Builder
    interface Builder {
        fun appDependencies(featureModuleDependency: FeatureModuleDependency): Builder
        fun build(): WeatherComponent
    }

    fun inject(weatherFragment: WeatherFragment?)
}