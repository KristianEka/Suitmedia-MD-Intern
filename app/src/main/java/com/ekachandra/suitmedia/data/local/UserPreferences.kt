package com.ekachandra.suitmedia.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = UserPreferences.USER_PREF)

class UserPreferences(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USERNAME] ?: ""
        }
    }

    suspend fun saveUser(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val USERNAME = stringPreferencesKey("username")
        const val USER_PREF = "user_pref"

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }

    }
}