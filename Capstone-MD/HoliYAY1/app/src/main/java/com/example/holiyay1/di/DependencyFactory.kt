package com.example.holiyay1.di

import com.example.holiyay1.data.HoliRepo
import com.example.holiyay1.data.api.ApiConfig

object DependencyFactory {
    fun provideRepo(): HoliRepo {
        val apiService = ApiConfig.getApiService()
        return HoliRepo(apiService)
    }
}
