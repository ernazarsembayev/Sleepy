package com.small.sleepysounds.database.dao

import androidx.room.*
import com.small.sleepysounds.database.models.Sound

@Dao
interface SoundDao {
    @Query("SELECT * FROM sound_table")
    suspend fun getSounds(): List<Sound>

    @Query("SELECT COUNT(id) FROM sound_table")
    suspend fun getCount(): Int

    @Query("SELECT * FROM sound_table WHERE id = :id")
    suspend fun getSoundById(id: Int): Sound

    @Delete
    suspend fun deleteSound(sounds: List<Sound>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateSound(sounds: List<Sound>)
}