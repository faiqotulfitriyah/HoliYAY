package com.example.holiyay1.data.api

import androidx.room.Entity

@Entity(tableName = "user")
data class User(
    val image: String,
    val username: String,
    val password: String,
    val email: String,
    val location: String
)
