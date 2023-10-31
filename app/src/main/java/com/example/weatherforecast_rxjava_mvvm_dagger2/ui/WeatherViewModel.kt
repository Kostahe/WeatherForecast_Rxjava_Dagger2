package com.example.weatherforecast_rxjava_mvvm_dagger2.ui

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherforecast_rxjava_mvvm_dagger2.di.AppComponent
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location.LocationTracker
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    private val disposables = CompositeDisposable()

    private var _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun apiCall() {
        locationTracker.getCurrentLocation().subscribe(object : SingleObserver<Location?> {
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }

            override fun onError(e: Throwable) {

            }

            override fun onSuccess(location: Location) {
                location.let {
                    repository.getWeatherData(it.latitude, it.longitude).subscribe(object : SingleObserver<State<Weather>> {
                        override fun onSubscribe(d: Disposable) {
                            disposables.add(d)
                        }

                        override fun onError(e: Throwable) {
                            _state.value.weather = State.Error(e.message.orEmpty())
                        }

                        override fun onSuccess(weatherState: State<Weather>) {
                            _state.value.weather = weatherState
                        }
                    })
                }
            }
        })
    }
}