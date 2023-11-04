package com.example.weatherforecast_rxjava_mvvm_dagger2

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast_rxjava_mvvm_dagger2.databinding.ActivityMainBinding
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.ViewModelFactory
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.WeatherViewModel
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.adapters.DailyWeatherAdapter
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.adapters.HourlyWeatherAdapter
import javax.inject.Inject

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: WeatherViewModel

    private val dailyWeatherAdapter by lazy {
        DailyWeatherAdapter()
    }
    private val hourlyWeatherAdapter by lazy {
        HourlyWeatherAdapter()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {}
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.hourlyWeatherRecyclerview.adapter = hourlyWeatherAdapter
        binding.dailyWeatherRecyclerview.adapter = dailyWeatherAdapter
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]
        viewModel.apiCall()

        viewModel.state.observe(this) { weatherState ->
            when(weatherState) {
                is State.Loading -> {
                    Log.d(TAG, "LOADING")
                }
                is State.Success -> {
                    Log.d(TAG, "SUCCESS")
                    weatherState.data?.let { weather ->
                        weather.currentWeather.apply {
                            binding.currentWeatherImage.setImageResource(weatherStatus.iconRes)
                            binding.currentWeatherWeathersStatus.text = getText(weatherStatus.weatherDesc)
                            binding.currentWeatherTemperature.text = temperature.toString()
                        }
                        hourlyWeatherAdapter.updateList(weather.hourly.weatherInfo)
                        dailyWeatherAdapter.updateList(weather.daily.weatherInfo)
                    }
                }
                is State.Error -> {
                    Log.d(TAG, "ERROR")
                }
            }
        }
    }
}