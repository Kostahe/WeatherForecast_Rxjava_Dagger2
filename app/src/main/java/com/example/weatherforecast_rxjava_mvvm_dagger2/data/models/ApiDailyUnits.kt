package com.example.weatherforecast_rxjava_mvvm_dagger2.data.models


import com.google.gson.annotations.SerializedName

data class ApiDailyUnits(
    @SerializedName("temperature_2m_max")
    val temperature2mMax: String,
    @SerializedName("temperature_2m_min")
    val temperature2mMin: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("weathercode")
    val weathercode: String
)