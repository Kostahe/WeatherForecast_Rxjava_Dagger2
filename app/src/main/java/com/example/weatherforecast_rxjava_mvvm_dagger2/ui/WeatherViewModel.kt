package com.example.weatherforecast_rxjava_mvvm_dagger2.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location.LocationTracker
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    init {
        locationTracker.getCurrentLocation().subscribe({

        }, {

        }, {

        }, { disposable ->
            compositeDisposable.add(disposable)
        })

    }
}