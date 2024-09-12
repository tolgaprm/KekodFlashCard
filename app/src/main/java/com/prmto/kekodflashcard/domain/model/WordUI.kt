package com.prmto.kekodflashcard.domain.model

data class WordUI(
    val id: Int,
    val englishWord: String,
    val turkishMean: String,
    val frenchMean: String,
    val imageUrl: String,
    val isFavorite: Boolean
)