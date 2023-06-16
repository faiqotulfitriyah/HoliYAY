package com.example.holiyay1.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.holiyay1.di.DependencyFactory
import com.example.holiyay1.ui.viewmodel.LoginViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(DependencyFactory.provideRepo()) as T
            }

            else -> throw IllegalArgumentException("Unknown")
        }
    }
}