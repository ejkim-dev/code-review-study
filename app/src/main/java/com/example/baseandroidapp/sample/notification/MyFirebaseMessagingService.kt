package com.example.baseandroidapp.sample.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.example.baseandroidapp.util.DLog
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.R
import android.app.Notification

import android.app.NotificationManager

import android.app.NotificationChannel
import android.os.Build
import android.text.TextUtils
import androidx.core.app.NotificationCompat

import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager





class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
//
//        val notificationManager = NotificationManagerCompat.from(
//            applicationContext
//        )
//
//        val CHANNEL_ID = ""
//        val CHANNEL_NAME = ""
//
//        val builder: NotificationCompat.Builder
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
//                val channel = NotificationChannel(
//                    CHANNEL_ID,
//                    CHANNEL_NAME,
//                    NotificationManager.IMPORTANCE_DEFAULT
//                )
//                notificationManager.createNotificationChannel(channel)
//            }
//            builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
//        } else {
//            builder = NotificationCompat.Builder(applicationContext)
//        }
//
        val title = message.notification?.title
        val body = message.notification?.body
//
//        builder.setContentTitle(title)
//            .setContentText(body)
//            .setSmallIcon(R.drawable.ic_delete)
//
//        val notification: Notification = builder.build()
//        notificationManager.notify(1, notification)


        DLog.e("test $title body $body")


        val it = Intent("EVENT_SNACKBAR")

        if (!TextUtils.isEmpty(title)) it.putExtra("title", title)

        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(it)

    }


}