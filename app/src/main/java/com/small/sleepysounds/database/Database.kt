package com.small.sleepysounds.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.small.sleepysounds.database.dao.SoundDao
import com.small.sleepysounds.database.dao.SoundGroupDao
import com.small.sleepysounds.database.models.Sound
import com.small.sleepysounds.database.models.SoundGroup

@Database(entities = [SoundGroup::class, Sound::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun soundDao(): SoundDao
    abstract fun soundGroupDao(): SoundGroupDao
}