package com.example.weatherforecast_rxjava_mvvm_dagger2.data.api

import android.database.Observable
import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(ApiConstants.END_POINT)
    fun getWeatherData(
        @Query(ApiParameters.LATITUDE) latitude: Double,
        @Query(ApiParameters.LONGITUDE) longitude: Double
    ) : Observable<Weather>
}