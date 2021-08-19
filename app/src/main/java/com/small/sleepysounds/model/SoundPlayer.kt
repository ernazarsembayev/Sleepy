package com.small.sleepysounds.model

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import com.small.sleepysounds.R
import com.small.sleepysounds.service.PlayerService
import java.lang.Exception

class SoundPlayer(val context: Context) {

    private var playingList = mutableListOf<PlayerService>()
    private val listIndex = mutableListOf<Int>()


    fun setSound(context: Context?, id: Int, audio: Int): Boolean{
        try {
            if (!listIndex.contains(id)) {

                if (context != null) {
                    if (listIndex.size < 8) {
                        val media1 = MediaPlayer.create(context, audio)
                        val media2 = MediaPlayer.create(context, audio)
                        listIndex.add(id)
                        val player = PlayerService(media1, media2)
                        playingList.add(player)
                        playingList[listIndex.indexOf(id)].play()
                    } else {
                        Toast.makeText(context, context.getText(R.string.out_of_list), Toast.LENGTH_SHORT).show()
                        return false
                    }
                } else return false

            } else{
                playingList[listIndex.indexOf(id)].stop()
                playingList.removeAt(listIndex.indexOf(id))
                listIndex.remove(id)
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
        return true
    }

    fun pauseAll() {
        for (i in playingList){
            i.pauseAll()
        }
    }

    fun playAll() {
        for (i in playingList){
            i.play()
        }
    }
}