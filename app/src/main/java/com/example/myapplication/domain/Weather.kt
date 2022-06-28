package com.example.myapplication.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Weather.TABLE_NAME )
data class Weather(
    val description: String,
    val icon: String,
    @PrimaryKey
    val id: Int,
    val main: String,
    var url:String
){
    companion object{
        const val TABLE_NAME = "Weather"
    }
}