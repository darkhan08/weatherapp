package com.example.weatherapp.data.db.repository


import androidx.lifecycle.LiveData
import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.data.db.MetricCurrentWeatherEntity
import com.example.weatherapp.data.db.room.WeatherDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WeatherRepository(private val database: WeatherDatabase) {
    val weather:LiveData<MetricCurrentWeatherEntity> = database.currentWeatherDao.getCurrentWeather()

    suspend fun refreshWeather() {
        withContext(Dispatchers.IO) {
            val currentWeather = WeatherApi.retrofitService.getWeather("almaty")
            database.currentWeatherDao.insert(currentWeather.asDatabaseModel())
        }
    }
}