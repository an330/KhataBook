package com.example.khatabook.domain.repository


import com.example.khatabook.data.local.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    fun getUser(): Flow<User?>


}
