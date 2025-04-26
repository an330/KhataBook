package com.example.khatabook.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import com.example.khatabook.domain.repository.ImageRepository

class ImageRepositoryImpl(context: Context): ImageRepository {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("image_prefs", Context.MODE_PRIVATE)

    override suspend fun saveImageUri(uri: Uri) {
        sharedPreferences.edit().putString("image_uri", uri.toString()).apply()
    }

    override suspend fun getSavedImageUri(): Uri? {
        val uriString = sharedPreferences.getString("image_uri", null)
        return uriString?.let { Uri.parse(it) }
    }
}