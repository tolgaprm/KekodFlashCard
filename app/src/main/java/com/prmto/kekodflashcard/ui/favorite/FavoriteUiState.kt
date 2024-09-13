package com.prmto.kekodflashcard.ui.favorite

import com.prmto.kekodflashcard.domain.model.WordUI

data class FavoriteUiState(
    val isLoading: Boolean = false,
    val words: List<WordUI> = emptyList()
)
