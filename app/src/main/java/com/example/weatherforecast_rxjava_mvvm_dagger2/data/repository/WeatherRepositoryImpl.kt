package com.example.weatherforecast_rxjava_mvvm_dagger2.data.repository

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.WeatherApi
import com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers.ApiWeatherMapper
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val apiWeatherMapper: ApiWeatherMapper
): WeatherRepository {

    override fun getWeatherData(
        latitude: Double,
        longitude: Double,
        result: (Single<Weather>) -> Unit
    ) {
        result.invoke(
            weatherApi.getWeatherData(latitude, longitude).map { apiWeather ->
                apiWeatherMapper.mapToDoMain(apiWeather)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }
}
