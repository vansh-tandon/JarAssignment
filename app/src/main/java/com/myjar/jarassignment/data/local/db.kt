package com.myjar.jarassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myjar.jarassignment.data.model.ComputerItem

@Database(entities = [ComputerItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}