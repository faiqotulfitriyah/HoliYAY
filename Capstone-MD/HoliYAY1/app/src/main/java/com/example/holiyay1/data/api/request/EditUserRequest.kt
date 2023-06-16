package com.example.holiyay1.data.api.request

import java.io.File

data class EditUserRequest(
    val username: String,
    val password: String,
    val email: String,
    val location: String,
    val image: String
)