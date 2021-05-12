package com.example.weatherapp.data.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.db.MetricCurrentWeatherEntity
import com.example.weatherapp.data.db.domain.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity: CurrentWeatherEntity)

    @Query("select * from current_weather")
    fun getCurrentWeather():LiveData<MetricCurrentWeatherEntity>
}