package com.example.khatabook.di


import android.content.Context
import androidx.room.Room
import com.example.khatabook.data.local.AppDatabase
import com.example.khatabook.data.local.NotificationPreferences
import com.example.khatabook.domain.repository.RoomRepository
import com.example.khatabook.domain.repository.UserRepository
import com.example.khatabook.data.remote.ApiService
import com.example.khatabook.data.local.dao.RoomDao
import com.example.khatabook.data.local.dao.UserDao
import com.example.khatabook.data.repository.ImageRepositoryImpl
import com.example.khatabook.data.repository.UserRepositoryImpl
import com.example.khatabook.data.repositoryImpl.api.RoomRepositoryImpl
import com.example.khatabook.domain.repository.ImageRepository
import com.example.khatabook.domain.useCase.ImageUseCase
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
    fun provideRoomRepository(
        api: ApiService,
        dao: RoomDao,
        @ApplicationContext context: Context,
        notificationPreferences: NotificationPreferences
    ): RoomRepository =
        RoomRepositoryImpl(api, dao, context, notificationPreferences)

    @Provides
    @Singleton
    fun provideImageRepository(@ApplicationContext context: Context): ImageRepository {
        return ImageRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideImageUseCase(repository: ImageRepository): ImageUseCase {
        return ImageUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideNotificationPreferences(@ApplicationContext context: Context): NotificationPreferences {
        return NotificationPreferences(context)
    }

}
