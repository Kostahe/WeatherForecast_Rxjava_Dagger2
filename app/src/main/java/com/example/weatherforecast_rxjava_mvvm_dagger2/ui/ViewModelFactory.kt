package com.example.weatherforecast_rxjava_mvvm_dagger2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location.LocationTracker
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository, locationTracker) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}