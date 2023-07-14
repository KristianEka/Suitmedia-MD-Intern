package com.ekachandra.suitmedia.ui.second_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ekachandra.suitmedia.data.AppRepository

class SecondViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getUser(): LiveData<String?> = appRepository.getUser().asLiveData()
}