package com.small.sleepysounds.activity

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.room.Room
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.small.sleepysounds.R
import com.small.sleepysounds.database.Database
import com.small.sleepysounds.database.FillDatabase
import com.small.sleepysounds.fragment.ScenesFragment
import com.small.sleepysounds.fragment.SettingsFragment
import com.small.sleepysounds.fragment.SoundsFragment
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var menuBottom: ChipNavigationBar

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room.databaseBuilder(applicationContext, Database::class.java, "sound-database").build()
        screenWidthInDp.apply {
            val scenesFragment = ScenesFragment(this)
            val soundsFragment = SoundsFragment(this, database)
            val settingsFragment = SettingsFragment()
            supportFragmentManager.beginTransaction().replace(R.id.scenes_frame, scenesFragment).commit()
            menuBottom = findViewById(R.id.menu_bottom)
            menuBottom.setItemSelected(R.id.home)
            menuBottom.setOnItemSelectedListener { id ->
                when (id) {
                    R.id.home -> supportFragmentManager.beginTransaction().replace(R.id.scenes_frame, scenesFragment).commit()
                    R.id.sound -> supportFragmentManager.beginTransaction().replace(R.id.scenes_frame, soundsFragment).commit()
                    R.id.settings -> supportFragmentManager.beginTransaction().replace(R.id.scenes_frame, settingsFragment).commit()
                }
            }
        }

        GlobalScope.launch {
            if(database.soundDao().getCount() == 0)
                FillDatabase(database)
        }
    }
}


val Activity.displayMetrics: DisplayMetrics
    get() {
        // display metrics is a structure describing general information
        // about a display, such as its size, density, and font scaling
        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= 30){
            display?.apply {
                getRealMetrics(displayMetrics)
            }
        }else{
            // getMetrics() method was deprecated in api level 30
            windowManager.defaultDisplay.getMetrics(displayMetrics)
        }

        return displayMetrics
    }

val Activity.screenWidthInDp: Int
    get() {
        var width: Int
        displayMetrics.apply {
            // screen width in dp
            width = (widthPixels / density).roundToInt()
        }

        return width
    }
