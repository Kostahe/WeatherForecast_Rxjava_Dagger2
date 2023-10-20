package com.example.weatherforecast_rxjava_mvvm_dagger2.data.models


import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("precipitation_probability")
    val precipitationProbability: List<Int>,
    @SerializedName("relativehumidity_2m")
    val relativeHumidity: List<Int>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("time")
    val time: List<Long>,
    @SerializedName("weathercode")
    val weatherCode: List<Int>,
    @SerializedName("windspeed_10m")
    val windSpeed: List<Double>
)