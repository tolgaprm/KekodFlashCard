package com.prmto.kekodflashcard.data.local

interface PreferencesRepository {

    fun isAppLaunchFirst(): Boolean

    fun setIsNotAppLaunchFirst()
}