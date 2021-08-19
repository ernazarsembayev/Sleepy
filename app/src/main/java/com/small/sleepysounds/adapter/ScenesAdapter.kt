package com.small.sleepysounds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.small.sleepysounds.model.DataModel
import com.small.sleepysounds.R

class ScenesAdapter(val width: Int) : RecyclerView.Adapter<ScenesAdapter.ViewHolder>() {

    private var dataList = emptyList<DataModel>()

    internal fun setDataList(dataList : List<DataModel>){
        this.dataList = dataList
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var image : ImageView = itemView.findViewById(R.id.iv_menu)
        var title : TextView = itemView.findViewById(R.id.tv_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scenes_menu_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.title.text = data.title
        holder.image.setImageResource(data.image)
    }

    override fun getItemCount() = dataList.size
}