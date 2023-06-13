package com.example.holiyay1.data.api

import retrofit2.http.*

interface ApiService {
    @POST("users/register")
    suspend fun registerUser(
        @Body user: User
    ): User

    @POST("users/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): User

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: String
    ): User

    @PUT("users/{id}")
    suspend fun editUser(
        @Path("id") id: String,
        @Body user: User
    ): User
}