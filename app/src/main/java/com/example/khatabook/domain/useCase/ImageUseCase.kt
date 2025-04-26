package com.example.khatabook.domain.useCase

import android.net.Uri
import com.example.khatabook.domain.repository.ImageRepository

class ImageUseCase(private val repository: ImageRepository) {
    suspend fun saveUri(uri: Uri) = repository.saveImageUri(uri)
    suspend fun getUri(): Uri? = repository.getSavedImageUri()
}