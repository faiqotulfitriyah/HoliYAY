package com.example.holiyay1.data.api

import androidx.room.Entity

@Entity(tableName = "user")
data class User(
    val id: String,
    val image: String,
    val name: String,
    val username: String,
    val email: String,
    val age: Int,
    val gender: String,
    val location: String
)
