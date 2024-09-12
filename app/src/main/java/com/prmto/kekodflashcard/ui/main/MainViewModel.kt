package com.prmto.kekodflashcard.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.common.UiText
import com.prmto.kekodflashcard.common.doOnError
import com.prmto.kekodflashcard.common.doOnLoading
import com.prmto.kekodflashcard.common.doOnSuccess
import com.prmto.kekodflashcard.domain.GetWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWordsUseCase: GetWordsUseCase
) : ViewModel() {
    private val _categoryItems = MutableStateFlow<List<CategoryItem>>(listOf())
    val categoryItems = _categoryItems.asStateFlow()

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

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

    fun getWords() {
        getWordsUseCase()
            .doOnLoading {
                _uiState.update {
                    it.copy(
                        loading = true,
                        errorMessage = null
                    )
                }
            }
            .doOnSuccess { words ->
                _uiState.update {
                    it.copy(
                        words = words,
                        loading = false,
                        errorMessage = null
                    )
                }
            }
            .doOnError { errorMessage ->
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = errorMessage
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}