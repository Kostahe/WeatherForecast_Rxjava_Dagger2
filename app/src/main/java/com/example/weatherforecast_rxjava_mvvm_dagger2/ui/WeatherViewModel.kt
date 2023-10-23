package com.example.weatherforecast_rxjava_mvvm_dagger2.ui

import android.location.Location
import androidx.lifecycle.ViewModel
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location.LocationTracker
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
            location?.let {
                repository.getWeatherData(location.latitude, location.longitude).subscribe({
                    _state = it
                })
            }

        }, {

        }, {

        }, { disposable ->
            compositeDisposable.add(disposable)
        })
    }
}

