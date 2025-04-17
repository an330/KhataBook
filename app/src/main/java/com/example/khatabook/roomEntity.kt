package com.example.khatabook

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val data: String? // You can serialize Map<String, Any> to JSON
)
