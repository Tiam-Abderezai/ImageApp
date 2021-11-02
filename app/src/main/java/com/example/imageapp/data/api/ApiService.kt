package com.example.imageapp.data.api
import com.example.imageapp.data.model.Image
import retrofit2.Call
import retrofit2.http.GET


//interface ApiService {
//    @GET("cats?count=100&urls=true&httpsUrls=true")
//    fun getImages(
//    ): Call<List<String>>
//
//}

interface ApiService {
    @GET("cats?count=100&urls=true&httpsUrls=true")
    suspend fun getImages(): List<Image>
}