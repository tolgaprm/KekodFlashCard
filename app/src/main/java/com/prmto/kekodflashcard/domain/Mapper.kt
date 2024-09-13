package com.prmto.kekodflashcard.domain

import com.prmto.kekodflashcard.data.local.entity.FavoriteWordEntity
import com.prmto.kekodflashcard.data.local.entity.LearnedWordEntity
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import com.prmto.kekodflashcard.domain.model.WordUI

fun WordUI.toWordEntity(): FavoriteWordEntity {
    return FavoriteWordEntity(
        id = id,
        englishWord = englishWord,
        turkishWord = turkishMean,
        frenchWord = frenchMean,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )
}

fun WordResponse.toWordUI(): WordUI {
    return WordUI(
        id = id,
        englishWord = englishWord,
        turkishMean = turkishMean,
        frenchMean = frenchMean,
        imageUrl = imageUrl,
        isFavorite = false
    )
}

fun WordUI.toLearnedWordEntity(): LearnedWordEntity {
    return LearnedWordEntity(
        id = id,
        englishWord = englishWord,
        turkishWord = turkishMean,
        frenchWord = frenchMean,
        imageUrl = imageUrl
    )
}

fun LearnedWordEntity.toWordUI(): WordUI {
    return WordUI(
        id = id,
        englishWord = englishWord,
        turkishMean = turkishWord,
        frenchMean = frenchWord,
        imageUrl = imageUrl,
        isFavorite = false
    )
}