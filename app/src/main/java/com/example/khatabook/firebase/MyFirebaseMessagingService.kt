package com.example.khatabook.firebase

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import android.net.Uri
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.khatabook.R
import com.example.khatabook.data.local.NotificationPreferences
import com.example.khatabook.di.NotificationPreferencesEntryPoint
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationPreferences: NotificationPreferences

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Check if notifications are enabled in DataStore
        runBlocking {
            val enabled = notificationPreferences.isNotificationEnabled.first()
            if (enabled) {
                remoteMessage.notification?.let {
                    // Check if the POST_NOTIFICATIONS permission is granted
                    if (ContextCompat.checkSelfPermission(
                            applicationContext,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED) {
                        sendNotification(it.title ?: "Title", it.body ?: "Message")
                    } else {
                        // Handle the case where permission is not granted
                        promptForPermission()
                    }
                }
            }
        }
    }

    // Prompt user to enable notification permission
    private fun promptForPermission() {
        // You can show a UI element asking the user to enable notifications
        // or take them directly to the app settings
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:${packageName}")
        }
        startActivity(intent)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun sendNotification(title: String, message: String) {
        val channelId = "default_channel"
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Send token to server for push notifications
    }
}