package com.example.holiyay1.data.api.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)