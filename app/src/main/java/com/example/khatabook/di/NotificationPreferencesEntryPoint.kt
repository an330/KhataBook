package com.example.khatabook.di

import com.example.khatabook.data.local.NotificationPreferences
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NotificationPreferencesEntryPoint {
    fun notificationPreferences(): NotificationPreferences
}