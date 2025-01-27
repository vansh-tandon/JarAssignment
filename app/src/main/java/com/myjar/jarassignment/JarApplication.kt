package com.myjar.jarassignment

import android.app.Application
import androidx.room.Room
import com.myjar.jarassignment.data.local.AppDatabase

class JarApplication: Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_database"
        ).build()
    }
}