package com.example.khatabook.data.repository


import com.example.khatabook.data.local.entity.User
import com.example.khatabook.data.local.dao.UserDao
import com.example.khatabook.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: User) = userDao.insertUser(user)
    override fun getUser(): Flow<User?> = userDao.getUser()
}
