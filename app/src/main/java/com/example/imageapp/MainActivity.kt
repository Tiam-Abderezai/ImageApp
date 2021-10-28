package com.example.imageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.api.Service
import com.example.imageapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    var imageList = ArrayList<Image>()
    var imageAdapter = ImageAdapter(this, imageList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this.applicationContext)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = imageAdapter

        val okHttpClient: OkHttpClient by lazy {
            val builder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
            builder.build()
        }

        val api = Retrofit.Builder()
            .baseUrl("https://shibe.online/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(Service::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getImages().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    withContext(Dispatchers.Main) {
                        if (!imageList.isEmpty()) {
                            imageList.clear()
                        }
                        var images: Image
                        for (i: Int in 0..data.size - 1) {
                            images = Image(
                                data?.get(i)!!.toString()
                            )
                            imageList.add(images)
                            // When successful, stop showing refresh
                            imageAdapter.notifyItemRangeChanged(
                                0,
                                imageAdapter.itemCount
                            )
                        }

                    }
                    response.raw().close()

                }
                response.raw().close()
            } catch (ex: Exception) {
                Log.d(TAG, ex.message!!)
            }
        }
    }
}