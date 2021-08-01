package com.example.memes.network

import com.example.memes.model.MemeResponseData
import retrofit2.http.GET

interface ApiService {
    @GET("gimme")
    suspend fun getMeme(): MemeResponseData?
}