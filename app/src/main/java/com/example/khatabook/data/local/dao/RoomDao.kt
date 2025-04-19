package com.example.khatabook.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.khatabook.data.local.entity.roomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<roomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<roomEntity>)

    @Update
    suspend fun update(item: roomEntity)

    @Delete
    suspend fun delete(item: roomEntity)
}
