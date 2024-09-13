package com.prmto.kekodflashcard.domain.learned

import com.prmto.kekodflashcard.data.repository.word.WordRepository
import com.prmto.kekodflashcard.domain.model.WordUI
import com.prmto.kekodflashcard.domain.toLearnedWordEntity
import javax.inject.Inject

class RemoveLearnedWordUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {

    suspend operator fun invoke(wordUI: WordUI) {
        wordRepository.deleteLearnedWord(wordUI.toLearnedWordEntity())
    }
}