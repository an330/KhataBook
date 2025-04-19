package com.example.khatabook.domain.repository

import com.example.khatabook.domain.model.roomItem
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun fetchAndSaveObjects()
    fun getObjects(): Flow<List<roomItem>>
    suspend fun updateObject(obj: roomItem)
    suspend fun deleteObject(obj: roomItem)
}