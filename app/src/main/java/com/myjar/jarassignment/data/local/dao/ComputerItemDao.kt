package com.myjar.jarassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myjar.jarassignment.data.remote.model.ComputerItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ComputerItemDao {
    @Query("SELECT * FROM computer_item")
    fun getAll(): Flow<List<ComputerItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items : List<ComputerItem>)
}