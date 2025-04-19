package com.example.khatabook.data.repositoryImpl.api

import com.example.khatabook.data.remote.ApiService
import com.example.khatabook.data.local.dao.RoomDao
import com.example.khatabook.domain.repository.RoomRepository
import com.example.khatabook.data.local.entity.roomEntity
import com.example.khatabook.domain.model.roomItem
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomRepositoryImpl(
    private val api: ApiService,
    private val dao: RoomDao
) : RoomRepository {

    private val gson = Gson()

    override suspend fun fetchAndSaveObjects() {
        val remoteList = api.getItems()
        val entities = remoteList.map {
            roomEntity(
                id = it.id,
                name = it.name,
                data = gson.toJson(it.data)
            )
        }
        dao.insertAll(entities)
    }

    override fun getObjects(): Flow<List<roomItem>> {
        return dao.getAll().map { entities ->
            entities.map {
                roomItem(
                    id = it.id,
                    name = it.name,
                    data = gson.fromJson(it.data ?: "{}", Map::class.java) as Map<String, Any>
                )
            }
        }
    }



    override suspend fun updateObject(obj: roomItem) {
        require(obj.name.isNotBlank()) { "Name must not be blank" }
        dao.update(
            roomEntity(
                id = obj.id,
                name = obj.name,
                data = gson.toJson(obj.data)
            )
        )
    }

    override suspend fun deleteObject(obj: roomItem) {
        dao.delete(
            roomEntity(
                id = obj.id,
                name = obj.name,
                data = gson.toJson(obj.data)
            )
        )
    }
}
