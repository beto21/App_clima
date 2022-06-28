package com.example.myapplication.data

import com.example.myapplication.domain.model.ClimateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DataClimate {


    @GET("/data/2.5/onecall")
    suspend fun getClimate(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("exclude") exclude: String,
        @Query("lang") lang:String = "sp"
    ): ClimateResponse

}