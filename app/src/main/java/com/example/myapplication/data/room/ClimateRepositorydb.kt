package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.domain.Weather

@Dao
interface ClimateRepositorydb {

    @Query("SELECT * From Weather WHERE id =:id")
    suspend fun getWeather(id:Int):Weather

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: Weather)
}