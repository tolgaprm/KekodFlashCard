package com.prmto.kekodflashcard.ui

import androidx.lifecycle.ViewModel
import com.prmto.kekodflashcard.data.local.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    fun finishOnboarding(): Boolean =
        preferencesRepository.isAppLaunchFirst()


}