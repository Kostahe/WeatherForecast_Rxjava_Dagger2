package com.example.weatherforecast_rxjava_mvvm_dagger2.ui

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location.LocationTracker
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private var _state = MutableStateFlow(WeatherState())
    val state = _state


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    init {
        locationTracker.getCurrentLocation().subscribe({ location: Location? ->
            location?.let { location: Location ->
                repository.getWeatherData(location.latitude, location.longitude).subscribe(
                    { weatherState: State<Weather> ->
                    _state.value.weather = weatherState
                    },{

                    })
            }
        }, {

        }, {
            Log.d("a", "")
        }, { disposable ->
            compositeDisposable.add(disposable)
        })
    }
}

