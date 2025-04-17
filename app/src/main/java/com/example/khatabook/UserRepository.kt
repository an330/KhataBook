package com.example.khatabook


import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    fun getUser(): Flow<User?>


}
