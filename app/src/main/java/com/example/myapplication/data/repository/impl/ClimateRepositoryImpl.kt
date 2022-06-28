package com.example.myapplication.data.repository.impl

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.DataClimate
import com.example.myapplication.data.repository.ClimateRepository
import com.example.myapplication.data.room.ClimateAppDb
import com.example.myapplication.domain.Weather
import com.example.myapplication.domain.model.Daily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.IDN
import javax.inject.Inject

class ClimateRepositoryImpl @Inject constructor(
    private val dataClimate: DataClimate,
    private val db: ClimateAppDb
) : ClimateRepository {
    override fun getClimate(): Flow<List<Daily>> = flow {
        val climate = dataClimate.getClimate(lat = "19.43", long = "-99.13", exclude = "hourly,current,minutely,alerts", )
        val dailys = climate.daily
        dailys.forEach { daily -> daily.weather.forEach { weather ->
            weather.url = BuildConfig.BASE_URL_ICON + weather.icon +".png"
            db.climateDao.insert(weather)
        } }

        emit(dailys)

    }.flowOn(Dispatchers.IO)

    override fun getWeather(id:Int): Flow<Weather> = flow {
        emit(db.climateDao.getWeather(id))
    }.flowOn(Dispatchers.IO)
}