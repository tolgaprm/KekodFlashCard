package com.prmto.kekodflashcard.ui.main

import androidx.annotation.DrawableRes
import com.prmto.kekodflashcard.common.UiText

data class CategoryItem(
    @DrawableRes val iconRes: Int,
    val title: UiText,
    val subtitle: UiText
)