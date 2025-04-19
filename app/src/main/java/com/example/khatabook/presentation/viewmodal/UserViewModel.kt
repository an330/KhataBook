package com.example.khatabook.presentation.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khatabook.data.local.entity.User
import com.example.khatabook.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun saveUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }
}
