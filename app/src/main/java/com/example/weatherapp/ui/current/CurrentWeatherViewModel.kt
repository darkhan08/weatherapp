package com.example.weatherapp.ui.current

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherapp.data.db.room.WeatherDatabase
import com.example.weatherapp.data.db.repository.WeatherRepository
import kotlinx.coroutines.launch
import java.io.IOException


class CurrentWeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = WeatherRepository(WeatherDatabase.getInstance(application))
    val weather = repository.weather
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromRepository()
    }
    private fun refreshDataFromRepository() =viewModelScope.launch {
        try {
            repository.refreshWeather()
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }
        catch (networkError:IOException) {
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}