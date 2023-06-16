<<<<<<< HEAD
package com.example.holiyay1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.holiyay1.data.HoliRepo

class LoginViewModel(private val holiRepos: HoliRepo) : ViewModel() {
    fun login(username: String, password: String) = holiRepos.loginUser(username, password)
=======
package com.example.holiyay1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.holiyay1.data.HoliRepo

class LoginViewModel(private val holiRepos: HoliRepo) : ViewModel() {
    fun login(username: String, password: String) = holiRepos.loginUser(username, password)
>>>>>>> 406bc2843e1c9b5899f6b7e83e117792bf548775
}