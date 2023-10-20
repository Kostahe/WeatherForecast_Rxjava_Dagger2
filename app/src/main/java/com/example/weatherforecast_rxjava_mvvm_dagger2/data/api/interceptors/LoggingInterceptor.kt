package com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.interceptors

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.d("LoggingInterceptor", "log: $message")
    }
}