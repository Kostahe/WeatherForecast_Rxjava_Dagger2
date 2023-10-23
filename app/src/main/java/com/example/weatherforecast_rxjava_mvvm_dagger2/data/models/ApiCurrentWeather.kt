package com.example.weatherforecast_rxjava_mvvm_dagger2.data.models


import com.google.gson.annotations.SerializedName

data class ApiCurrentWeather(
    @SerializedName("interval")
    val interval: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("time")
    val time: Long,
    @SerializedName("weathercode")
    val weatherCode: Int,
    @SerializedName("winddirection")
    val windDirection: Double,
    @SerializedName("windspeed")
    val windSpeed: Double
)