package com.example.khatabook.firebase

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.khatabook.R
import com.example.khatabook.data.local.NotificationPreferences
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject

class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationPreferences: NotificationPreferences

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Check if notifications are enabled in DataStore
        if (notificationPreferences.isNotificationsEnabled()) {
            remoteMessage.notification?.let {
                sendNotification(it.title ?: "Title", it.body ?: "Message")
            }
        }
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