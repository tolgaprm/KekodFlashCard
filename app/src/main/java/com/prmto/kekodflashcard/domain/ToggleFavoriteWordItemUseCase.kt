package com.prmto.kekodflashcard.domain

import com.prmto.kekodflashcard.data.repository.word.WordRepository
import com.prmto.kekodflashcard.domain.model.WordUI
import javax.inject.Inject

class ToggleFavoriteWordItemUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {

    suspend operator fun invoke(wordUI: WordUI) {
        if (wordUI.isFavorite) {
            wordRepository.deleteFavoriteWord(wordUI)
        } else {
            wordRepository.insertFavoriteWord(wordUI)
        }
    }
}