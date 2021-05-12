package com.example.weatherapp.ui.current

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CurrentWeatherViewModelFactory(val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CurrentWeatherViewModel::class.java))
            return CurrentWeatherViewModel(application) as T
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}