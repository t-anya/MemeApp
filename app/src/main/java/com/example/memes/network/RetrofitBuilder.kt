package com.example.memes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val MEME_URL = " https://meme-api.herokuapp.com/"

    private fun getRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MEME_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofitBuilder().create(ApiService::class.java)
}