package com.small.sleepysounds.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class PlayerService (private var mediaPlayer1: MediaPlayer?, private var mediaPlayer2: MediaPlayer?) :
    Service() {

    fun play(){
        try {
            mediaPlayer1?.start()

            Timer("SettingUp", false).schedule(
                if(mediaPlayer2 != null) {
                    (mediaPlayer2!!.duration - 2420).toLong()
                } else {0}
            ) {
                mediaPlayer2?.start()
            }

            mediaPlayer1?.setOnCompletionListener {
                Timer("SettingUp", false).schedule(
                    if(mediaPlayer1 != null) {
                        (mediaPlayer1!!.duration - 4420).toLong()
                    } else {0}
                ) {
                    if (mediaPlayer1 != null) {
                        mediaPlayer1!!.start()
                    }
                }
            }

            mediaPlayer2?.setOnCompletionListener {
                Timer("SettingUp", false).schedule(
                    if(mediaPlayer2 != null) {
                            (mediaPlayer2!!.duration - 4420).toLong()
                    } else {0}
                ) {
                    if (mediaPlayer2 != null) {
                        mediaPlayer2!!.start()
                    }
                }
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun stop() {
        mediaPlayer1?.stop()
        mediaPlayer1?.release()
        mediaPlayer1 = null
        mediaPlayer2?.stop()
        mediaPlayer2?.release()
        mediaPlayer2 = null
    }

    fun pauseAll() {
        if (mediaPlayer1 != null && mediaPlayer1!!.isPlaying) {
            mediaPlayer1?.stop()
        }
        if (mediaPlayer2 != null && mediaPlayer2!!.isPlaying)
            mediaPlayer2?.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}