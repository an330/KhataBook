package com.example.khatabook



import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KhatabookApp : Application(){
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        // Initialize FirebaseMessaging for token generation and FCM
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default_channel",
                "Default Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is the default channel for app notifications."
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
