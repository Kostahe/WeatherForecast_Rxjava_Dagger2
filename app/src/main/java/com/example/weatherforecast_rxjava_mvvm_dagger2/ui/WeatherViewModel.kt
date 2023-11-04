package com.example.weatherforecast_rxjava_mvvm_dagger2.ui

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location.LocationTracker
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    private val disposables = CompositeDisposable()

    private val _state = MutableLiveData<State<Weather>>()
    val state: LiveData<State<Weather>>
        get() = _state

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun apiCall() {
        _state.value = State.Loading()
        locationTracker.getCurrentLocation().subscribe(object : SingleObserver<Location?> {
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }

            override fun onError(e: Throwable) {
                _state.value = State.Error("Location Error")
            }

            override fun onSuccess(location: Location) {
                location.let { location ->
                    repository.getWeatherData(location.latitude, location.longitude) { state ->
                        state.subscribe(object: SingleObserver<Weather> {
                            override fun onSubscribe(d: Disposable) {
                                disposables.add(d)
                            }

                            override fun onError(e: Throwable) {
                                _state.value = State.Error("Data error")
                            }

                            override fun onSuccess(weather: Weather) {
                                _state.value =  State.Success(weather)
                            }
                        })
                    }
                }
            }
        })
    }
}

//.subscribe(object : SingleObserver<State<Weather>> {
//    override fun onSubscribe(d: Disposable) {
//        disposables.add(d)
//    }
//
//    override fun onError(e: Throwable) {
//        _state.value.weather = State.Error(e.message.orEmpty())
//    }
//
//    override fun onSuccess(weatherState: State<Weather>) {
//        _state.value.weather = weatherState
//    }
//})