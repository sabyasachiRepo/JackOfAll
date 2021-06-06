package com.tools.weather

import com.tools.core.BaseFragment
import com.tools.weather.databinding.FragmentWeatherBinding


class WeatherFragment : BaseFragment<FragmentWeatherBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }

    override fun getFragmentLayout() = R.layout.fragment_weather

    override fun getToolBar() = binding.appbar.toolbar

}