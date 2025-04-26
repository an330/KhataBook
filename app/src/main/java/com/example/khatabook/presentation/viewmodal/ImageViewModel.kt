package com.example.khatabook.presentation.viewmodal

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khatabook.domain.useCase.ImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageUseCase: ImageUseCase
) : ViewModel() {

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> = _imageUri

    fun setImageUri(uri: Uri) {
        viewModelScope.launch {
            imageUseCase.saveUri(uri)
            _imageUri.value = uri
        }
    }

    fun loadImageUri() {
        viewModelScope.launch {
            _imageUri.value = imageUseCase.getUri()
        }
    }
}