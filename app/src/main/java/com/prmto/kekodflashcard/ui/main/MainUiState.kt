package com.prmto.kekodflashcard.ui.main

import com.prmto.kekodflashcard.domain.model.WordUI

data class MainUiState(
    val loading: Boolean = false,
    val words: List<WordUI> = listOf(),
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val searchedWords: List<WordUI> = listOf(),
    val wordsBeforeSearch: List<WordUI> = listOf()
)
