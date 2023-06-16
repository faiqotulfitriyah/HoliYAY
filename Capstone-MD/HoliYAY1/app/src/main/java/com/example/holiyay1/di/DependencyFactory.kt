<<<<<<< HEAD
package com.example.holiyay1.di

import com.example.holiyay1.data.HoliRepo
import com.example.holiyay1.data.api.ApiConfig

object DependencyFactory {
    fun provideRepo(): HoliRepo {
        val apiService = ApiConfig.getApiService()
        return HoliRepo(apiService)
    }
}
=======
package com.example.holiyay1.di

import com.example.holiyay1.data.HoliRepo
import com.example.holiyay1.data.api.ApiConfig

object DependencyFactory {
    fun provideRepo(): HoliRepo {
        val apiService = ApiConfig.getApiService()
        return HoliRepo(apiService)
    }
}
>>>>>>> 406bc2843e1c9b5899f6b7e83e117792bf548775
