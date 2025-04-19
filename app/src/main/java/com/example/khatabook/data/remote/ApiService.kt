package com.example.khatabook.data.remote

import com.example.khatabook.domain.model.roomItem
import retrofit2.http.GET

interface ApiService {
    @GET("objects")
    suspend fun getItems(): List<roomItem>
}
