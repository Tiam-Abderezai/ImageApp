package com.example.imageapp.data.repository

import com.example.imageapp.data.api.ApiService
import com.example.imageapp.data.model.Image
import retrofit2.Call
import javax.inject.Inject

class ImageRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getImages(): List<String> {
        return apiService.getImages()
    }
}
