package com.ekachandra.suitmedia.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ekachandra.suitmedia.data.local.UserPreferences
import com.ekachandra.suitmedia.data.remote.paging.UsersPagingSource
import com.ekachandra.suitmedia.data.remote.response.DataItem
import com.ekachandra.suitmedia.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences,
) {

    fun getAllUser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                UsersPagingSource(apiService)
            }
        ).liveData
    }

    suspend fun saveUser(username: String) {
        userPreferences.saveUser(username)
    }

    fun getUser(): Flow<String?> = userPreferences.getUser()
}