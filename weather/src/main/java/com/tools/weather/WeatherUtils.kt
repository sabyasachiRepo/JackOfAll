package com.tools.weather


object WeatherUtils {
    fun getIcon(iconCode: String): Int {
        return when (iconCode) {
            "01d" -> R.drawable.weather_01d
            "01n" -> R.drawable.weather_01n
            "02d" -> R.drawable.weather_02d
            "02n" -> R.drawable.weather_02n
            "03d" -> R.drawable.weather_03d
            "04d" -> R.drawable.weather_04d
            "09d" -> R.drawable.weather_09d
            "10d" -> R.drawable.weather_10d
            "10n" -> R.drawable.weather_10n
            "11d" -> R.drawable.weather_11d
            "13d" -> R.drawable.weather_13d
            "50d" -> R.drawable.weather_50d
            else -> R.drawable.weather_01d
        }
    }


}