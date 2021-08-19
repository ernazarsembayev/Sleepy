package com.small.sleepysounds.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.small.sleepysounds.R
import com.small.sleepysounds.adapter.SoundAdapter
import com.small.sleepysounds.adapter.SoundGroupAdapter
import com.small.sleepysounds.database.Database
import com.small.sleepysounds.database.models.Sound
import com.small.sleepysounds.database.models.relationships.SoundGroupWithSounds
import com.small.sleepysounds.model.SoundPlayer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SoundsFragment(private val width: Int, val database: Database) : Fragment(), SoundAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var soundGroupAdapter: SoundGroupAdapter
    private var soundGroupWithSoundsList = mutableListOf<SoundGroupWithSounds>()
    private lateinit var parentContext: Context
    private lateinit var soundPlayer: SoundPlayer
    private lateinit var playPause: ImageView


    lateinit var db: Database

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentContext = context
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)

        soundPlayer = SoundPlayer(parentContext)

        soundGroupAdapter = SoundGroupAdapter(width)
        recyclerView.adapter = soundGroupAdapter

        GlobalScope.launch {
            if (soundGroupWithSoundsList.isEmpty())
                soundGroupWithSoundsList = database.soundGroupDao().getAllSoundGroupWithSounds() as MutableList<SoundGroupWithSounds>

            soundGroupAdapter.setDataList(soundGroupWithSoundsList, this@SoundsFragment)
        }

        playPause = view.findViewById(R.id.play_pause)
        playPause.setImageResource(R.drawable.pause)
        playPause.tag = "pause"
        playPause.setOnClickListener {
            if (playPause.tag == "pause") {
                soundPlayer.pauseAll()
                playPause.tag = "play"
                playPause.setImageResource(R.drawable.play)
            } else {
                soundPlayer.playAll()
                playPause.tag = "pause"
                playPause.setImageResource(R.drawable.pause)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sounds, container, false)
    }

    override fun onItemClick(sound: Sound, v: View) {
        //TODO оптимизация
        if (soundPlayer.setSound(context, sound.id!!, sound.rowAudio))
            showHide(v)
    }


    private fun showHide(view: View) {
        val seekBar = view.findViewById<SeekBar>(R.id.sb_volume)
        val imageView = view.findViewById<ImageView>(R.id.iv_sound_img)

        if (seekBar.visibility == View.VISIBLE) {
            seekBar.visibility = View.INVISIBLE
            imageView.background = ContextCompat.getDrawable(requireContext(), R.drawable.sound_item_default_background)
            imageView.alpha = 0.6F
        } else{
            seekBar.visibility = View.VISIBLE
            imageView.background = ContextCompat.getDrawable(requireContext(), R.drawable.sound_item_check_background)
            imageView.alpha = 1f
        }
    }
}