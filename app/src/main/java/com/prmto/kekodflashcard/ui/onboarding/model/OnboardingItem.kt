package com.prmto.kekodflashcard.ui.onboarding.model

import android.os.Parcelable
import com.prmto.kekodflashcard.common.UiText
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnboardingItem(
    val title: UiText,
    val description: UiText,
    val lottieUrl: String
):Parcelable