package com.example.holiyay1.data.api

import com.example.holiyay1.data.api.request.*
import com.example.holiyay1.data.api.response.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("users/register")
    suspend fun registerUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): SignUpResponse

    @POST("users/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("users/")
    suspend fun getAllUsers(): UserResponse

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: String
    ): UserResponse

    @PUT("users/{id}")
    suspend fun editUser(
        @Path("id") id: String,
        @Body request: EditUserRequest
    ): UserResponse

    @GET("locations/")
    suspend fun getAllLocations(): Response<LocationResponse>

    @GET("locations/{id}")
    suspend fun getLocationById(
        @Path("id") id: String
    ): LocationResponse

    @PUT("locations/{id}")
    suspend fun editLocation(
        @Path("id") id: String,
        @Body request: Location
    ): LocationResponse

    @POST("locations/recommendation")
    suspend fun getRecommendation(
        @Body request: RecommendationRequest
    ): Response<RecommendationResponse>
}
