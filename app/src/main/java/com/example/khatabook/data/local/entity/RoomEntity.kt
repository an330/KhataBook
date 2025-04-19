package com.example.khatabook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class roomEntity(
    @PrimaryKey val id: String,
    val name: String,
    val data: String? // You can serialize Map<String, Any> to JSON
)
