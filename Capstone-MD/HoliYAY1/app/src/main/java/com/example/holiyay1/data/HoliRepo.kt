package com.example.holiyay1.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.holiyay1.data.api.ApiService
import com.example.holiyay1.data.api.Outcome
import com.example.holiyay1.data.api.request.EditUserRequest
import com.example.holiyay1.data.api.response.SignUpResponse
import com.example.holiyay1.data.api.response.UserResponse

class HoliRepo(private val apiService: ApiService) {

    fun loginUser(username: String, password: String): LiveData<Outcome<String>> = liveData {
        emit(Outcome.Loading)
        try {
            apiService.loginUser(username, password)
            emit(Outcome.Success("Login successful"))
        } catch (e: Exception) {
            Log.e("HoliRepo", "loginUser: ${e.message.toString()}")
            emit(Outcome.Error(e.message.toString()))
        }
    }

    fun postSignUp(username: String, password: String): LiveData<Outcome<SignUpResponse>> = liveData {
        emit(Outcome.Loading)
        try {
            val response = apiService.registerUser(username, password)
            emit(Outcome.Success(response))
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "postRegister: ${e.message.toString()}")
            emit(Outcome.Error(e.message.toString()))
        }
    }

    fun getUserById(userId: String): LiveData<Outcome<UserResponse>> = liveData {
        emit(Outcome.Loading)
        try {
            val response = apiService.getUserById(userId)
            emit(Outcome.Success(response))
        } catch (e: Exception) {
            Log.e("HoliRepo", "getUserById: ${e.message.toString()}")
            emit(Outcome.Error(e.message.toString()))
        }
    }

    fun editUser(
        userId: String,
        image: String,
        username: String,
        password: String,
        email: String,
        location: String
    ): LiveData<Outcome<UserResponse>> = liveData {
        emit(Outcome.Loading)
        try {
            val request = EditUserRequest(image, username, password, email, location)
            val response = apiService.editUser(userId, request)
            emit(Outcome.Success(response))
        } catch (e: Exception) {
            Log.e("HoliRepo", "editUser: ${e.message.toString()}")
            emit(Outcome.Error(e.message.toString()))
        }
    }
}