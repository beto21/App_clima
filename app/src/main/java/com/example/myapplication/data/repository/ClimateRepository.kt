package com.example.myapplication.data.repository

import com.example.myapplication.domain.Weather
import com.example.myapplication.domain.model.Daily
import kotlinx.coroutines.flow.Flow

interface ClimateRepository {

    fun getClimate():  Flow<List<Daily>>
    fun getWeather(id:Int): Flow<Weather>
}