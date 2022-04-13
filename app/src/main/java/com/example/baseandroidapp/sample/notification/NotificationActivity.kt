package com.example.baseandroidapp.sample.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.baseandroidapp.R
import java.lang.Exception

class NotificationActivity : AppCompatActivity() {

    lateinit var mMessageReceiver: BroadcastReceiver

    companion object {
        fun callingIntent(context: Context) = Intent(context, NotificationActivity::class.java)
    }


    private val CHANNEL_ID = "TEST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        createNotificationChannel()

        val notificationId = 1


        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val button: Button = findViewById(R.id.btn_notification)
        button.setOnClickListener {
//            var builder = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle("textTitle")
//                .setContentText("textContent")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)


            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)


            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationId, builder.build())
            }

        }

        mMessageReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                try{
                    // Do something
                    button.text = intent.getStringExtra("title")
                    Toast.makeText(
                        this@NotificationActivity,
                        "${intent.getStringExtra("title")}",
                        Toast.LENGTH_LONG
                    ).show()
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mMessageReceiver, IntentFilter("EVENT_SNACKBAR"));

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}