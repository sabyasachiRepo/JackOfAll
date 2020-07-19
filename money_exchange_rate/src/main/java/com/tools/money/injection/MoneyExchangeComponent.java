package com.tools.money.injection;


import com.tools.money.ExchangeRateFragment;

import dagger.Component;

@Component(modules = MoneyExchangeModule.class)
public interface MoneyExchangeComponent {
    void inject(ExchangeRateFragment exchangeRateFragment);
}
