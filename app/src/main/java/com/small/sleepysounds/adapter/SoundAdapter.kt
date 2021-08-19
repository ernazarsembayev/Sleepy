package com.small.sleepysounds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.small.sleepysounds.R
import com.small.sleepysounds.database.models.Sound

class SoundAdapter : RecyclerView.Adapter<SoundAdapter.ViewHolder>() {

    private var soundList = emptyList<Sound>()
    private lateinit var listener: OnItemClickListener

    internal fun setDataList(soundList : List<Sound>, listener: OnItemClickListener){
        this.soundList = soundList
        this.listener = listener
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var image : ImageView = itemView.findViewById(R.id.iv_sound_img)
        var title : TextView = itemView.findViewById(R.id.tv_sound_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = layoutPosition
            soundList[position]
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(soundList[position], v!!)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(sound: Sound, v: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sounds_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = soundList[position]

        holder.title.setText(data.resString)
        holder.image.setImageResource(data.resDrawable)

    }

    override fun getItemCount() = soundList.size

}