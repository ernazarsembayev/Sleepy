package com.small.sleepysounds.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.small.sleepysounds.R

class CreateNotification {

//    companion object {
//        private const val CHANNEL_ID = "channel"
//        private const val actionPrevious = "actionPrevious"
//        private const val channelPlay = "actionPlay"
//        private const val channelNext = "actionNext"
//
//        lateinit var notification: Notification
//
//        fun createNotification(context: Context, track: Track, playButton: Int, pos: Int, Size: Int) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                var notificationManagerCompat = NotificationManagerCompat.from(context)
//                var mediaSessionCommand = MediaSessionCompat(context, "tag")
//
//                val icon = BitmapFactory.decodeResource(context.resources, track.getImage())
//
//                var pendingIntent: PendingIntent?
//                var drwPrevious: Int
//                if (pos == 0) {
//                    pendingIntent = null
//                    drwPrevious = 0
//                } else {
//
//                }
//
//                // create notification
//                notification = NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.ic_round_music_note_24)
//                    .setContentTitle(track.getTitle())
//                    .setContentText(track.getArtist())
//                    .setLargeIcon(icon)
//                    .setOnlyAlertOnce(true)
//                    .setShowWhen(false)
//                    .setPriority(NotificationCompat.PRIORITY_LOW)
//                    .build()
//
//                notificationManagerCompat.notify(1, notification)
//
//            }
//        }
//    }
}