package com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location

import android.location.Location
import io.reactivex.Observable
import io.reactivex.Single

interface LocationTracker {
    fun getCurrentLocation(): Observable<Location?>
}