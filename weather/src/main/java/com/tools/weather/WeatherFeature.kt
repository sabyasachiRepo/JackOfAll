package com.tools.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tools.core.CoreLib
import com.tools.core.StaticFeature
import com.tools.weather.WeatherFragment.Companion.newInstance

class WeatherFeature(var context: Context) : StaticFeature {
    override fun getFeatureEntryPoint(parent: ViewGroup?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.weather_entry_point_view, parent, false)
        view.setOnClickListener { v: View? -> CoreLib.communicationLib?.replaceFragment(newInstance(), true) }
        return view
    }
}