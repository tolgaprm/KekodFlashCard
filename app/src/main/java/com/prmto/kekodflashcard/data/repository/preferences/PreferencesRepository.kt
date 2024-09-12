package com.prmto.kekodflashcard.data.repository.preferences

interface PreferencesRepository {

    fun isAppLaunchFirst(): Boolean

    fun setIsNotAppLaunchFirst()
}