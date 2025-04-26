package com.example.khatabook.domain.repository

import android.net.Uri

interface ImageRepository {
    suspend fun saveImageUri(uri: Uri)
    suspend fun getSavedImageUri(): Uri?
}