package com.example.memes

import com.example.memes.network.ApiHelper

class MemeRepository(val apiHelper: ApiHelper) {
    suspend fun getMemes() = apiHelper.getMemes()
}