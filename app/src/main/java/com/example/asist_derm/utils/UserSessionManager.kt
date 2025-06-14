package com.example.asist_derm.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.asist_derm.data.model.UserData
import kotlinx.coroutines.flow.first

object UserSessionManager {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    private val UID_KEY = stringPreferencesKey("uid")
    private val USERNAME_KEY = stringPreferencesKey("username")
    private val EMAIL_KEY = stringPreferencesKey("email")

    suspend fun saveUser(context: Context, user: UserData) {
        context.dataStore.edit { prefs ->
            prefs[UID_KEY] = user.uid
            prefs[USERNAME_KEY] = user.username
            prefs[EMAIL_KEY] = user.email
        }
    }

    suspend fun getUser(context: Context): UserData? {
        val prefs = context.dataStore.data.first()
        val uid = prefs[UID_KEY]
        val username = prefs[USERNAME_KEY]
        val email = prefs[EMAIL_KEY]

        return if (uid != null && username != null && email != null) {
            UserData(uid, username, email)
        } else {
            null
        }
    }

    suspend fun clearUser(context: Context) {
        context.dataStore.edit { it.clear() }
    }
}