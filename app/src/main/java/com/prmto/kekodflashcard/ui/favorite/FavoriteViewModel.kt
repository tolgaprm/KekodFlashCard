package com.prmto.kekodflashcard.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.kekodflashcard.domain.GetFavoriteWordsUseCase
import com.prmto.kekodflashcard.domain.ToggleFavoriteWordItemUseCase
import com.prmto.kekodflashcard.domain.model.WordUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteWordsUseCase: GetFavoriteWordsUseCase,
    private val toggleFavoriteWordItemUseCase: ToggleFavoriteWordItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState = _uiState.asStateFlow()

    fun getFavoriteWords() {
        _uiState.update { it.copy(isLoading = true) }
        getFavoriteWordsUseCase()
            .onEach { words ->
                _uiState.update {
                    it.copy(
                        words = words,
                        isLoading = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onFavoriteClick(item: WordUI) {
        viewModelScope.launch {
            toggleFavoriteWordItemUseCase(item)
        }
    }
}