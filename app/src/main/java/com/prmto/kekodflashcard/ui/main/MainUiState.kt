package com.prmto.kekodflashcard.ui.main

import com.prmto.kekodflashcard.data.remote.response.WordResponse

data class MainUiState(
    val loading: Boolean = false,
    val words: List<WordResponse> = listOf(),
    val errorMessage: String? = null
)
