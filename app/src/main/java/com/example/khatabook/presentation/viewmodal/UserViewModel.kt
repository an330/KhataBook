package com.example.khatabook.presentation.viewmodal

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khatabook.data.local.entity.User
import com.example.khatabook.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    fun saveUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
            Log.d("UserViewModel", "User saved and state updated: $user")
            _user.value = user // <-- Notify UI user is saved
        }
    }
}
