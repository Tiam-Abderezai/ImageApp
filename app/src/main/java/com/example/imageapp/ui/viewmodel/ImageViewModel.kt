package com.example.imageapp.ui.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.lifecycle.liveData
import com.example.imageapp.Resource
import com.example.imageapp.data.api.ApiService
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
