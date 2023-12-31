package com.example.weatherforecast_rxjava_mvvm_dagger2.utils

import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.WeatherStatus
import java.text.SimpleDateFormat
import java.util.Locale

object FormatUtils {

    fun formatTime(pattern: String, time: Long): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(time*1000)
    }

    fun getWeatherStatus(weatherCode: Int): WeatherStatus {
        return when(weatherCode) {
            0 -> WeatherStatus.ClearSky
            1 -> WeatherStatus.MainlyClear
            2 -> WeatherStatus.PartlyCloudy
            3 -> WeatherStatus.Overcast
            45 -> WeatherStatus.Foggy
            48 -> WeatherStatus.DepositingRimeFog
            51 -> WeatherStatus.LightDrizzle
            53 -> WeatherStatus.ModerateDrizzle
            55 -> WeatherStatus.DenseDrizzle
            56 -> WeatherStatus.LightFreezingDrizzle
            57 -> WeatherStatus.DenseFreezingDrizzle
            61 -> WeatherStatus.SlightRain
            63 -> WeatherStatus.ModerateRain
            65 -> WeatherStatus.HeavyRain
            66 -> WeatherStatus.LightFreezingDrizzle
            67 -> WeatherStatus.HeavyFreezingRain
            71 -> WeatherStatus.SlightSnowFall
            73 -> WeatherStatus.ModerateSnowFall
            75 -> WeatherStatus.HeavySnowFall
            77 -> WeatherStatus.SnowGrains
            80 -> WeatherStatus.SlightRainShowers
            81 -> WeatherStatus.ModerateRainShowers
            82 -> WeatherStatus.ViolentRainShowers
            85 -> WeatherStatus.SlightSnowShowers
            86 -> WeatherStatus.HeavySnowShowers
            95 -> WeatherStatus.ModerateThunderstorm
            96 -> WeatherStatus.SlightHailThunderstorm
            99 -> WeatherStatus.HeavyHailThunderstorm
            else -> WeatherStatus.ClearSky
        }
    }
}