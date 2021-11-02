package com.example.imageapp.di

import com.example.imageapp.data.api.ApiService
import com.example.imageapp.data.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService): ImageRepository {
        return ImageRepository(apiService)
    }
}