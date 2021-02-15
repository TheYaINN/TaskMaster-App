package de.taskmaster.activity.app

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import de.taskmaster.R

class PushNotificationManager(private val activity: Activity) {

    private val channel = "TASKMASTER_NOT"

    init {
        createNotificationChannel()
        //TODO: get all TaskLists of the logged in user, then check if there should be any list with a deadline of today
        //TODO: implement a possibility to check if the user is near a location, that is in the vicinity of his lists, if so send notification
    }

    private fun createNotificationChannel() {
        val name = activity.getString(R.string.channel_name)
        val descriptionText = activity.getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channel, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(activity, channel)
            .setSmallIcon(R.drawable.app_icon)
            .setContentTitle("Notifications Example")
            .setContentText("This is a test notification")

        val notificationIntent = Intent(activity, AppActivity::class.java)
        builder.setContentIntent(PendingIntent.getActivity(activity, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT))

        val manager = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }
}
