package com.prmto.kekodflashcard.domain

import com.prmto.kekodflashcard.data.repository.word.WordRepository
import com.prmto.kekodflashcard.domain.model.WordUI
import javax.inject.Inject

class InsertLearnedWordUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {

    suspend operator fun invoke(wordUI: WordUI) {
        wordRepository.insertLearnedWord(wordUI.toLearnedWordEntity())
    }
}