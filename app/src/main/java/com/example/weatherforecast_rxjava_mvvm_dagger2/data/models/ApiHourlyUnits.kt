package com.example.weatherforecast_rxjava_mvvm_dagger2.data.models


import com.google.gson.annotations.SerializedName

data class ApiHourlyUnits(
    @SerializedName("precipitation_probability")
    val precipitationProbability: String,
    @SerializedName("relativehumidity_2m")
    val relativehumidity2m: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("weathercode")
    val weathercode: String,
    @SerializedName("windspeed_10m")
    val windspeed10m: String
)