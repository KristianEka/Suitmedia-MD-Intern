package com.ekachandra.suitmedia

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ekachandra.suitmedia.data.AppRepository
import com.ekachandra.suitmedia.data.di.Injection
import com.ekachandra.suitmedia.ui.second_screen.SecondViewModel
import com.ekachandra.suitmedia.ui.third_screen.ThirdViewModel

class ViewModelFactory private constructor(
    private val appRepository: AppRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdViewModel::class.java)) {
            return ThirdViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(SecondViewModel::class.java)) {
            return SecondViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(
            context: Context,
        ): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(
                        Injection.getRepository(context)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}