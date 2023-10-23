package com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models

data class CurrentWeather(
    val temperature: Double,
    val time: String,
    val day: String,
    val weatherStatus: WeatherStatus,
    val windSpeed: Double
)
