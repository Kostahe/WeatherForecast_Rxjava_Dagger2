package com.example.weatherforecast_rxjava_mvvm_dagger2

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast_rxjava_mvvm_dagger2.databinding.ActivityMainBinding
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.WeatherViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {}
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        val weatherState = viewModel.state.value.weather

        when(weatherState) {
            is State.Loading -> {}
            is State.Success -> {binding.textView.text = weatherState.data.toString()}
            is State.Error -> {}
        }
    }
}