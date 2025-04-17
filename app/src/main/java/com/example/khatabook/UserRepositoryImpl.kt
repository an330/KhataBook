package com.example.khatabook


import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: User) = userDao.insertUser(user)
    override fun getUser(): Flow<User?> = userDao.getUser()
}
