package com.example.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.domain.Weather

@Database(
    entities =
    [Weather::class, ], version = 1, exportSchema = false
)
abstract class ClimateAppDb :RoomDatabase(){
    abstract val climateDao : ClimateRepositorydb
}