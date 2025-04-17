package com.example.khatabook

import retrofit2.http.GET

interface ApiService {
    @GET("objects")
    suspend fun getItems(): List<roomItem>
}
