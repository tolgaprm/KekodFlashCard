package com.prmto.kekodflashcard.data.repository.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
) : PreferencesRepository {
    override fun isAppLaunchFirst(): Boolean {
        return preferences.getBoolean(IS_APP_LAUNCH_FIRST, true)
    }

    override fun setIsNotAppLaunchFirst() {
        preferences.edit().putBoolean(IS_APP_LAUNCH_FIRST, false).apply()
    }

    companion object PreferencesKey {
        private const val IS_APP_LAUNCH_FIRST = "IS_APP_LAUNCH_FIRST"
    }
}