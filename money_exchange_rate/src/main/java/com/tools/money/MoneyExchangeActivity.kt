package com.tools.money

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tools.money_exchange_rate.R

class MoneyExchangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_exchange)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.money_exchange_container, ExchangeRateFragment.newInstance(), null)
        fragmentTransaction.commit()
    }
}