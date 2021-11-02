package com.example.imageapp.data.repository

import com.example.imageapp.data.api.ApiService
import com.example.imageapp.data.model.Image
import retrofit2.Call
import javax.inject.Inject

//class ImageRepository @Inject constructor(private val service: ApiService) {
//    suspend fun getImages(): Call<List<String>> {
//        return service.getImages()
//    }
//}

class ImageRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getImages(): List<Image> {
        return apiService.getImages()
    }
}