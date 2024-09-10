package com.prmto.kekodflashcard.ui.main

data class MainUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)