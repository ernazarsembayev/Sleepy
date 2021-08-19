package com.small.sleepysounds.database.dao

import androidx.room.*
import com.small.sleepysounds.database.models.Sound
import com.small.sleepysounds.database.models.SoundGroup
import com.small.sleepysounds.database.models.relationships.SoundGroupWithSounds

@Dao
interface SoundGroupDao {
    @Query("SELECT * FROM sound_group_table")
    suspend fun getSoundGroups(): List<SoundGroup>

    @Query("SELECT * FROM sound_group_table WHERE id = :id")
    suspend fun getSoundGroupById(id: Int): SoundGroup

    @Delete
    suspend fun deleteSoundGroup(sound: SoundGroup)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateSoundGroup(sound: List<SoundGroup>)

    @Transaction
    @Query("SELECT * FROM sound_group_table WHERE id = :id")
    suspend fun getSoundGroupWithSounds(id: Int): List<SoundGroupWithSounds>

    @Transaction
    @Query("SELECT * FROM sound_group_table")
    suspend fun getAllSoundGroupWithSounds(): List<SoundGroupWithSounds>

//    @Query("SELECT sound_group_table.name, sound_table.* FROM sound_group_table INNER JOIN sound_table ON sound_group_table.id = sound_table.parent_id")
//    suspend fun getSoundGroups(): List<com.small.sleepysounds.model.SoundGroup>
}