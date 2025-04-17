package com.example.khatabook

import AppDatabase
import android.content.Context
import androidx.room.Room
import com.example.khatabook.data.repositoryImpl.api.RoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "khatabook.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideRoomDao(db: AppDatabase): RoomDao = db.itemDao()

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository =
        UserRepositoryImpl(userDao)

    @Provides
    fun provideRoomRepository(api: ApiService, dao: RoomDao): RoomRepository =
        RoomRepositoryImpl(api, dao)
}
