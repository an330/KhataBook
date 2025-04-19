package com.example.khatabook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.khatabook.data.local.entity.User
import com.example.khatabook.data.local.dao.RoomDao
import com.example.khatabook.data.local.entity.roomEntity
import com.example.khatabook.data.local.dao.UserDao


@Database(entities = [User::class, roomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun itemDao(): RoomDao
}
