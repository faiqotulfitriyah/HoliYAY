package com.example.holiyay1.data.api.response

import com.example.holiyay1.data.api.RecLocation
import com.google.gson.annotations.SerializedName

data class RecommendationResponse(
    @field:SerializedName("recommendation")
    val recommendation: List<RecLocation>,
    @field:SerializedName("message")
    val message: String
)