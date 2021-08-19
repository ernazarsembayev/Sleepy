package com.small.sleepysounds.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sound_table")
data class Sound (

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "res_string")
    val resString: Int,

    @ColumnInfo(name = "res_drawable")
    val resDrawable: Int,

    @ColumnInfo(name = "row_audio")
    val rowAudio: Int,

    @ColumnInfo(name = "parent_id")
    val parentId: Int
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}