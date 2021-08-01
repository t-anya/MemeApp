package com.example.memes.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.memes.MemeRepository
import com.example.memes.model.MemeResponseData
import com.example.memes.utils.Resource
import kotlinx.coroutines.Dispatchers

class MemeViewModel(val memeRepository: MemeRepository): ViewModel() {

    var memeResponseData: MemeResponseData? = null

    fun getMemes() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try{
            emit(Resource.success(memeRepository.getMemes()))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message ?: "Error"))
        }
    }
}