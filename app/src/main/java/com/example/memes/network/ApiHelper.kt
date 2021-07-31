package com.example.memes.network

class ApiHelper(private val apiService: ApiService) {
    suspend fun getMemes() = apiService.getMeme()
}