package com.example.memes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memes.MemeRepository
import com.example.memes.network.ApiHelper

class ViewModelFactory(val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemeViewModel::class.java)) {
            return MemeViewModel(MemeRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}