package com.tools.money.injection

import com.tools.jackofall.di.FeatureModuleDependency
import com.tools.money.ExchangeRateFragment
import dagger.Component

@Component(dependencies = [FeatureModuleDependency::class], modules = [MoneyExchangeModule::class])
interface MoneyExchangeComponent {

    @Component.Builder
    interface Builder {
        fun appDependencies(featureModuleDependency: FeatureModuleDependency): Builder
        fun build(): MoneyExchangeComponent
    }

    fun inject(exchangeRateFragment: ExchangeRateFragment)
}