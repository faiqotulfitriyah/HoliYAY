package com.example.holiyay1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.holiyay1.data.HoliRepo

class LoginViewModel(private val holiRepos: HoliRepo) : ViewModel() {
    fun login(username: String, password: String) = holiRepos.loginUser(username, password)
}