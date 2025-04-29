package com.example.khatabook.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val DATASTORE_NAME = "user_preferences"

// Extension property outside the class — scoped to the application context
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

@Singleton
class NotificationPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val NOTIFICATION_ENABLED = booleanPreferencesKey("notification_enabled")

    val isNotificationEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[NOTIFICATION_ENABLED] ?: true // Default is ON
        }

    suspend fun setNotificationEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATION_ENABLED] = enabled
        }
    }
}