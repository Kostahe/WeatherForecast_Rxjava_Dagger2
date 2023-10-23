package com.example.weatherforecast_rxjava_mvvm_dagger2.data.models


import com.google.gson.annotations.SerializedName

data class ApiDaily(
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerializedName("time")
    val time: List<Long>,
    @SerializedName("weathercode")
    val weatherCode: List<Int>
)