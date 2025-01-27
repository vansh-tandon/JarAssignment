package com.myjar.jarassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.myjar.jarassignment.data.model.ComputerItem
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM computer_item")
    fun getAll(): Flow<List<ComputerItem>>

    @Insert
    suspend fun insertAll(items : List<ComputerItem>)
}