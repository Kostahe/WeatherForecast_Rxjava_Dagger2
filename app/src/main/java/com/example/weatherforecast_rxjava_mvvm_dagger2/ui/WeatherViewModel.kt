package com.example.weatherforecast_rxjava_mvvm_dagger2.ui

import android.location.Location
import androidx.lifecycle.ViewModel
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location.LocationTracker
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class WeatherViewModel: ViewModel() {
    @Inject
    lateinit var repository: WeatherRepository
    @Inject
    lateinit var locationTracker: LocationTracker

    private val disposables = CompositeDisposable()

    private var _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()


    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    init {
        locationTracker.getCurrentLocation().subscribe(object : SingleObserver<Location?> {
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }

            override fun onError(e: Throwable) {
                _state.value.weather = State.Error(e.message.orEmpty())
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

