package com.example.myapplication.di

import com.example.myapplication.data.DataClimate
import com.example.myapplication.data.repository.ClimateRepository
import com.example.myapplication.data.repository.impl.ClimateRepositoryImpl
import com.example.myapplication.data.room.ClimateAppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepositoryMovie(dataClimate: DataClimate, db: ClimateAppDb): ClimateRepository {
        return ClimateRepositoryImpl(dataClimate, db)
    }
}