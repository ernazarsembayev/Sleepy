package com.small.sleepysounds.database.models.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.small.sleepysounds.database.models.Sound
import com.small.sleepysounds.database.models.SoundGroup

data class SoundGroupWithSounds(
    @Embedded val soundGroup: SoundGroup,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_id"
    )
    val sound: List<Sound>
)