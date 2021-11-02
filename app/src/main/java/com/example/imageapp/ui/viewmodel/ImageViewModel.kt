package com.example.imageapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.lifecycle.liveData
import com.example.imageapp.Resource
import com.example.imageapp.data.model.Image
import com.example.imageapp.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class ImageViewModel @Inject constructor(
    private val mainRepository: ImageRepository
) : ViewModel() {
    fun fetchImages() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = mainRepository.getImages()))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }
}
//@HiltViewModel
//class ImageViewModel @Inject constructor(private val mainRepository: ImageRepository) : ViewModel(){
//    private val url = MutableLiveData<List<String>>()
//    var imageList = ArrayList<Image>()
//    private val TAG = "ImageViewModel"
//
//
//

//    var imageAdapter = ImageAdapter(appl , imageList)

//    viewModelScope.launch {
//        // Coroutine that will be canceled when the ViewModel is cleared.
//    }

//    fun fetchImages() = liveData(Dispatchers.IO) {
////        emit(Resource.loading(null))
//        try {
//            mainRepository.getImages()
//        } catch (exception: Exception) {
////            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
//        }
//    }


//    fun fetchImages() {
//        val okHttpClient: OkHttpClient by lazy {
//            val builder = OkHttpClient.Builder()
//            val loggingInterceptor = HttpLoggingInterceptor()
//            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            builder.addInterceptor(loggingInterceptor)
//            builder.build()
//        }
//
//        val api = Retrofit.Builder()
//            .baseUrl("https://shibe.online/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//            .create(Service::class.java)
//
//                viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = api.getImages().awaitResponse()
//                if (response.isSuccessful) {
//                    val data = response.body()!!
//                    withContext(Dispatchers.Main) {
//                        if (!imageList.isEmpty()) {
//                            imageList.clear()
//                        }
//                        var images: Image
//                        for (i: Int in 0..data.size - 1) {
//                            images = Image(
//                                data?.get(i)!!.toString()
//                            )
//                            imageList.add(images)
//                            // When successful, stop showing refresh
//                            imageAdapter.notifyItemRangeChanged(
//                                0,
//                                imageAdapter.itemCount
//                            )
//                        }
//
//                    }
//                    response.raw().close()
//
//                }
//                response.raw().close()
//            } catch (ex: Exception) {
//                Log.d(TAG, ex.message!!)
//            }
//        }
//
//    }
//}