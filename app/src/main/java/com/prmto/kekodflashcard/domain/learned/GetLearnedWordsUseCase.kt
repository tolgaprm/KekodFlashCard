package com.prmto.kekodflashcard.domain.learned

import com.prmto.kekodflashcard.data.repository.word.WordRepository
import com.prmto.kekodflashcard.domain.model.WordUI
import com.prmto.kekodflashcard.domain.toWordUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetLearnedWordsUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {

    operator fun invoke(): Flow<List<WordUI>> {
        return combine(
            wordRepository.getLearnedWords(),
            wordRepository.getFavoriteWords()
        ) { learnedWords, favoriteWords ->
            learnedWords.map {
                it.toWordUI()
                    .copy(
                        isFavorite = favoriteWords.any { favoriteWord -> favoriteWord.id == it.id }
                    )
            }
        }
    }
}