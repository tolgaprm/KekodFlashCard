package com.prmto.kekodflashcard.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.common.UiText
import com.prmto.kekodflashcard.data.repository.preferences.PreferencesRepository
import com.prmto.kekodflashcard.ui.onboarding.model.OnboardingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _onboardingItems = MutableStateFlow<List<OnboardingItem>>(listOf())
    val onboardingItems = _onboardingItems.asStateFlow()

    private val _viewEvent = Channel<OnboardingEvent>()
    val viewEvent = _viewEvent.receiveAsFlow()

    init {
        _onboardingItems.value = listOf(
            OnboardingItem(
                title = UiText.StringRes(R.string.hello),
                description = UiText.StringRes(R.string.onboarding_1_description),
                lottieUrl = "https://lottie.host/25590b65-7f45-474a-a155-905118a4192a/OgCPwoqnss.json"
            ),
            OnboardingItem(
                title = UiText.StringRes(R.string.onboarding_2_title),
                description = UiText.StringRes(R.string.onboarding_2_description),
                lottieUrl = "https://lottie.host/272c9ff8-627c-48e2-8d64-cafde87494fc/4G1sxNRMMw.json"
            ),
            OnboardingItem(
                title = UiText.StringRes(R.string.onboarding_3_title),
                description = UiText.StringRes(R.string.onboarding_3_description),
                lottieUrl = "https://lottie.host/5863f8ab-988a-46a2-a835-18720b7b5e2d/iw9WGCBest.json"
            )
        )
    }

    fun finishOnboarding() {
        preferencesRepository.setIsNotAppLaunchFirst()
        viewModelScope.launch {
            _viewEvent.send(OnboardingEvent.FinishOnboarding)
        }
    }
}

sealed class OnboardingEvent {
    data object FinishOnboarding : OnboardingEvent()
}