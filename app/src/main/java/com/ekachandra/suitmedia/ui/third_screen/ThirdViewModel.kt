package com.ekachandra.suitmedia.ui.third_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ekachandra.suitmedia.data.AppRepository
import com.ekachandra.suitmedia.data.remote.response.DataItem
import kotlinx.coroutines.launch

class ThirdViewModel(private val appRepository: AppRepository) : ViewModel() {

    val users: LiveData<PagingData<DataItem>> = appRepository.getAllUser().cachedIn(viewModelScope)

    fun saveUser(username: String) {
        viewModelScope.launch {
            appRepository.saveUser(username)
        }
    }
}