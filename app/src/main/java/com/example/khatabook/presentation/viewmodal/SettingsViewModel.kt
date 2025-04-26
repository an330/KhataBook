package com.example.khatabook.presentation.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khatabook.data.local.NotificationPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val notificationPreferences: NotificationPreferences
) : ViewModel() {

    val isNotificationEnabled = notificationPreferences.isNotificationEnabled

    fun toggleNotification(enabled: Boolean) {
        viewModelScope.launch {
            notificationPreferences.setNotificationEnabled(enabled)
        }
    }
}