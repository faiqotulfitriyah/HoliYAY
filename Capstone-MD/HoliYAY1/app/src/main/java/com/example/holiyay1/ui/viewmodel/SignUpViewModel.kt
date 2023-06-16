package com.example.holiyay1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.holiyay1.data.HoliRepo

class SignUpViewModel(private val holiRepos: HoliRepo) : ViewModel() {
    fun signUp(username: String, password: String) = holiRepos.postSignUp(username, password)
}