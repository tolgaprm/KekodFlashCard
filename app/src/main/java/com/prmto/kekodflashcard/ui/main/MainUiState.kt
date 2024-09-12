package com.prmto.kekodflashcard.ui.main

import com.prmto.kekodflashcard.domain.model.WordUI

data class MainUiState(
    val loading: Boolean = false,
    val words: List<WordUI> = listOf(),
    val errorMessage: String? = null
)
