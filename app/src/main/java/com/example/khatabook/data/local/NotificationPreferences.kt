package com.example.khatabook.data.local

import android.content.Context
import kotlinx.coroutines.flow.Flow

class NotificationPreferences(private val context: Context) {

    companion object {
        private const val DATASTORE_NAME = "user_preferences"
        private val Context.dataStore by preferencesDataStore(DATASTORE_NAME)
        val NOTIFICATION_ENABLED = booleanPreferencesKey("notification_enabled")
    }

    val isNotificationEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[NOTIFICATION_ENABLED] ?: true  // By default, notifications are ON
        }

    suspend fun setNotificationEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATION_ENABLED] = enabled
        }
    }
}