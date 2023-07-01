package com.cicd.employee.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.cicd.employee.R
import com.cicd.employee.ui.MainActivity
import kotlinx.coroutines.*

class ForegroundService : Service() {

    private val CHANNEL_ID = "ForegroundService Kotlin"

    companion object {
        private var job: Job? = null
        fun startService(context: Context, message: String) {
            val startIntent = Intent(context, ForegroundService::class.java)
            startIntent.putExtra("inputExtra", message)
            ContextCompat.startForegroundService(context, startIntent)
            var count = 0
            job = CoroutineScope(Dispatchers.Default).launch {
                while (true) {
                    delay(1000)
                    Log.d("ForegroundService", "count: ${count++}")
                }
            }
        }

        fun stopService(context: Context) {
            job?.cancel()
            val stopIntent = Intent(context, ForegroundService::class.java)
            context.stopService(stopIntent)

        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //do heavy work on a background thread
        Log.d("ForegroundService", "onStartCommand: ")
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service Kotlin Example")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        //stopSelf();
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("ForegroundService", "onBind: ")
        return null
    }

    override fun onDestroy() {
        Log.d("ForegroundService", "onDestroy: ")
        super.onDestroy()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }

    }





}