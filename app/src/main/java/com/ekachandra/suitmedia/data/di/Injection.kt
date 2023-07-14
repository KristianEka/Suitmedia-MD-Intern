package com.ekachandra.suitmedia.data.di

import android.content.Context
import com.ekachandra.suitmedia.data.AppRepository
import com.ekachandra.suitmedia.data.local.UserPreferences
import com.ekachandra.suitmedia.data.local.dataStore
import com.ekachandra.suitmedia.data.remote.retrofit.ApiConfig

object Injection {
    fun getRepository(context: Context): AppRepository {
        return AppRepository(
            apiService = ApiConfig.getApiService(),
            userPreferences = UserPreferences.getInstance(dataStore = context.dataStore)
        )
    }
}