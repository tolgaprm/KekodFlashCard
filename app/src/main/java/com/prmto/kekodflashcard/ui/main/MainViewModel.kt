package com.prmto.kekodflashcard.ui.main

import androidx.lifecycle.ViewModel
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.common.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _categoryItems = MutableStateFlow<List<CategoryItem>>(listOf())
    val categoryItems = _categoryItems.asStateFlow()

    init {
        setCategoryItems()
    }

    private fun setCategoryItems() {
        _categoryItems.value = listOf(
            CategoryItem(
                iconRes = R.drawable.ic_star_yellow,
                title = UiText.StringRes(R.string.my_favorites),
                subtitle = UiText.StringRes(R.string.my_favorites_subtitle, listOf("0"))
            ),
            CategoryItem(
                iconRes = R.drawable.ic_ai_yellow,
                title = UiText.StringRes(R.string.exercise_with_ai),
                subtitle = UiText.StringRes(R.string.exercise_with_ai_subtitle)
            )
        )
    }
}