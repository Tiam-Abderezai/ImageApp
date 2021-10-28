package com.example.imageapp.api
import com.example.imageapp.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Service {
    @GET("cats?count=100&urls=true&httpsUrls=true")
    fun getImages(
    ): Call<List<String>>

}