package com.prmto.kekodflashcard.ui.learned

import com.prmto.kekodflashcard.domain.model.WordUI

data class MyLearnedUiState(
    val isLoading: Boolean = false,
    val learnedWords: List<WordUI> = emptyList(),
    val searchedWords: List<WordUI> = emptyList(),
    val wordsBeforeSearch: List<WordUI> = listOf()
)
