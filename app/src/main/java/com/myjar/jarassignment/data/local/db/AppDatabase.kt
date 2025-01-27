package com.myjar.jarassignment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myjar.jarassignment.data.local.dao.ComputerItemDao
import com.myjar.jarassignment.data.remote.model.ComputerItem

@Database(entities = [ComputerItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun computerItemDao(): ComputerItemDao
}