package com.example.holiyay1.data.api

import com.google.gson.annotations.SerializedName

data class RecLocation(
    @field:SerializedName("_id")
    val placeId: Int,
    @field:SerializedName("place_name")
    val placeName: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("category")
    val category: String,
    @field:SerializedName("city")
    val city: String,
    @field:SerializedName("price")
    val price: String,
    @field:SerializedName("rating")
    val rating: Double,
    @field:SerializedName("lat")
    val lat: Double,
    @field:SerializedName("long")
    val long: Double,
    @field:SerializedName("image")
    val image: String
)
