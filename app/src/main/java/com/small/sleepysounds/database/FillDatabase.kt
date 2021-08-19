package com.small.sleepysounds.database

import android.util.Log
import com.small.sleepysounds.R
import com.small.sleepysounds.database.models.Sound
import com.small.sleepysounds.database.models.SoundGroup
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class FillDatabase(val database: Database) {


    private val soundGroupList: List<SoundGroup> = listOf(
        SoundGroup(1, "nature", R.string.nature),
        SoundGroup(2, "water", R.string.water),
        SoundGroup(3, "rain", R.string.rain),
        SoundGroup(4, "animal", R.string.animal),
        SoundGroup(5, "city_noises", R.string.city_noises),
        SoundGroup(6, "instruments", R.string.instruments),
        SoundGroup(7, "meditation", R.string.meditation)
    )

    private val soundList: List<Sound> = listOf(
        // Nature
        Sound("bird_forest", R.string.bird_forest, R.drawable.bird_forest, R.raw.bird_forest, 1),
        Sound("rain_forest", R.string.rain_forest, R.drawable.rain_forest, R.raw.rain_forest, 1),
        Sound("night_forest", R.string.night_forest, R.drawable.night_forest, R.raw.night_forest, 1),
        Sound("fire", R.string.fire, R.drawable.fire, R.raw.fire, 1),
        Sound("cave", R.string.cave, R.drawable.cave, R.raw.cave, 1),
        Sound("leaves", R.string.leaves, R.drawable.leaves, R.raw.leaves, 1),

        // Water
        Sound("creek", R.string.creek, R.drawable.creek, R.raw.creek, 2),
        Sound("calm_sea", R.string.calm_sea, R.drawable.calm_sea, R.raw.calm_sea, 2),
        Sound("under_water", R.string.under_water, R.drawable.under_water, R.raw.under_water, 2),
        Sound("cold_wind", R.string.cold_wind, R.drawable.cold_wind, R.raw.wind_chime, 2),
        Sound("waterfall", R.string.waterfall, R.drawable.waterfall, R.raw.waterfall, 2),
        Sound("water_drop", R.string.water_drop, R.drawable.water_drop, R.raw.water_drop, 2),

        // Rain
        Sound("light_rain", R.string.light_rain, R.drawable.light_rain, R.raw.light_rain, 3),
        Sound("heavy_rain", R.string.heavy_rain, R.drawable.heavy_rain, R.raw.heavy_rain, 3),
        Sound("thunder", R.string.thunder, R.drawable.thunder, R.raw.thunder, 3),
        Sound("rain_on_leaves", R.string.rain_on_leaves, R.drawable.rain_leaves, R.raw.rain_on_leaves, 3),
        Sound("rain_on_roof", R.string.rain_on_roof, R.drawable.rain_on_roof, R.raw.rain_on_roof, 3),
        Sound("rain_on_windows", R.string.rain_on_windows, R.drawable.rain_on_windows, R.raw.rain_on_window, 3),

        // Animal
        Sound("wren", R.string.wren, R.drawable.bird1, R.raw.wren, 4),
        Sound("woodlark", R.string.woodlark, R.drawable.bird2, R.raw.woodlark, 4),
        Sound("seagull", R.string.seagull, R.drawable.seagull, R.raw.seagull, 4),
        Sound("wolves", R.string.wolves, R.drawable.wolf, R.raw.wolf, 4),
        Sound("crickets", R.string.crickets, R.drawable.cricket, R.raw.cricket, 4),
        Sound("whale", R.string.whale, R.drawable.whale, R.raw.whale, 4),

        // City & noises
        Sound("airplane", R.string.airplane, R.drawable.airplane, R.raw.airplane, 5),
        Sound("clock", R.string.clock, R.drawable.clock, R.raw.clock, 5),
        Sound("restaurant", R.string.restaurant, R.drawable.restaurant, R.raw.restaurant, 5),
        Sound("white_noise", R.string.white_noise, R.drawable.white_noise, R.raw.white_noise, 5),
        Sound("railway", R.string.railway, R.drawable.railway, R.raw.train, 5),
        Sound("binaural_alpha", R.string.binaural_alpha, R.drawable.binaural_alpha, R.raw.binaural_alpha, 5),

        // Instruments
        Sound("bell", R.string.bell, R.drawable.bell, R.raw.bell, 6),
        Sound("piano", R.string.piano, R.drawable.piano, R.raw.piano, 6),
        Sound("violin", R.string.violin, R.drawable.violin, R.raw.violin, 6),
        Sound("magic_music", R.string.magic_music, R.drawable.magic_music, R.raw.magic_music, 6),
        Sound("flute", R.string.flute, R.drawable.flute, R.raw.flute, 6),
        Sound("wind_chime", R.string.wind_chime, R.drawable.wind_chime, R.raw.wind_chime, 6),

        // Meditation
        Sound("meditation_bowl", R.string.meditation_bowl, R.drawable.bowl, R.raw.meditation_bowl, 7),
        Sound("dreaming", R.string.dreaming, R.drawable.dreaming, R.raw.dreaming_8d, 7),
        Sound("delta_waves", R.string.delta_waves, R.drawable.delta_waves, R.raw.delta_waves, 7),
        Sound("brain_massage", R.string.brain_massage, R.drawable.brain_massage, R.raw.brain_massage, 7),
        Sound("healing_sleep", R.string.healing_sleep, R.drawable.healing_sleep, R.raw.healing_sleep, 7),
        Sound("mindfulness", R.string.mindfulness, R.drawable.mindfull, R.raw.mindfulness, 7),
    )

    init {
        GlobalScope.launch {
            database.soundDao().insertOrUpdateSound(soundList)
            database.soundGroupDao().insertOrUpdateSoundGroup(soundGroupList)
            getSounds()
        }
    }

    private suspend fun getSounds() {
        val sounds: List<Sound> = database.soundDao().getSounds()
        Log.e("sounds", (sounds.size).toString())
        val soundGroups: List<SoundGroup> = database.soundGroupDao().getSoundGroups()
        Log.e("soundGroups", (soundGroups.size).toString())
    }
}