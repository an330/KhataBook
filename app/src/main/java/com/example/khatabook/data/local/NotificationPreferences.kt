package com.example.khatabook.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

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