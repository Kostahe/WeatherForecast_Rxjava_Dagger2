package com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository

import android.database.Observable
import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.Weather
import io.reactivex.Single

interface WeatherRepository {
    fun getWeatherData(latitude: Double, longitude: Double) : Single<Weather>
}