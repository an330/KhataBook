package com.example.khatabook.presentation.viewmodal

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khatabook.data.local.NotificationPreferences
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val notificationPreferences: NotificationPreferences
) : ViewModel() {

    val isNotificationEnabled: Flow<Boolean> = notificationPreferences.isNotificationEnabled

    init {
        fetchFcmToken()
    }

    fun toggleNotifications(enabled: Boolean) {
        viewModelScope.launch {
            notificationPreferences.setNotificationEnabled(enabled)
        }
    }

    private fun fetchFcmToken() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    Log.d("FCM_TOKEN", "FCM Token: $token")
                } else {
                    Log.e("FCM_TOKEN", "Fetching FCM token failed", task.exception)
                }
            }
    }
}
