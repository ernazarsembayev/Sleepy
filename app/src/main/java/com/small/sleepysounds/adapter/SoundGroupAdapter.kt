package com.small.sleepysounds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.small.sleepysounds.R
import com.small.sleepysounds.database.models.relationships.SoundGroupWithSounds

class SoundGroupAdapter(val width: Int) : RecyclerView.Adapter<SoundGroupAdapter.ViewHolder>() {

    private var soundGroupWithSounds = emptyList<SoundGroupWithSounds>()
    private lateinit var listener: SoundAdapter.OnItemClickListener

    internal fun setDataList(soundGroupWithSounds: List<SoundGroupWithSounds>, listener: SoundAdapter.OnItemClickListener){
        this.soundGroupWithSounds = soundGroupWithSounds
        this.listener = listener
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val recyclerView : RecyclerView = itemView.findViewById(R.id.rv_sub_item)
        val title : TextView = itemView.findViewById(R.id.tv_sound_group)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sounds_menu_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val soundGroupWithSounds = soundGroupWithSounds[position]

        holder.title.setText(soundGroupWithSounds.soundGroup.resString)

        if (width > 600) {
            holder.recyclerView.layoutManager = GridLayoutManager(holder.recyclerView.context, 6)
        } else {
            holder.recyclerView.layoutManager = GridLayoutManager(holder.recyclerView.context, 3)
        }


        val soundAdapter = SoundAdapter()
        holder.recyclerView.adapter = soundAdapter
        soundAdapter.setDataList(soundGroupWithSounds.sound, listener)
    }

    override fun getItemCount() = soundGroupWithSounds.size
}