package com.example.imageapp.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getImages() = apiService.getImages()
}