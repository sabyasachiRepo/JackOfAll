package com.tools.money.injection

import com.tools.jackofall.di.AppComponent
import com.tools.money.ExchangeRateFragment
import dagger.Component

@ExchangeScope
@Component(dependencies = [AppComponent::class], modules = [MoneyExchangeModule::class])
interface MoneyExchangeComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MoneyExchangeComponent
    }

    fun inject(exchangeRateFragment: ExchangeRateFragment)
}