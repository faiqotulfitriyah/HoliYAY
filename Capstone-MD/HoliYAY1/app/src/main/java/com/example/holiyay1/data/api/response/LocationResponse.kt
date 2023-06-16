package com.example.holiyay1.data.api.response

import com.example.holiyay1.data.api.Location
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @field:SerializedName("locations")
    val location: List<Location>,
    @field:SerializedName("message")
    val message: String
)