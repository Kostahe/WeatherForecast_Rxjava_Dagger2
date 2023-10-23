package com.example.weatherforecast_rxjava_mvvm_dagger2.data.models


import com.google.gson.annotations.SerializedName

data class ApiWeather(
    @SerializedName("current_weather")
    val apiCurrentWeather: ApiCurrentWeather,
    @SerializedName("current_weather_units")
    val apiCurrentWeatherUnits: ApiCurrentWeatherUnits,
    @SerializedName("daily")
    val apiDaily: ApiDaily,
    @SerializedName("daily_units")
    val apiDailyUnits: ApiDailyUnits,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("hourly")
    val apiHourly: ApiHourly,
    @SerializedName("hourly_units")
    val apiHourlyUnits: ApiHourlyUnits,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)