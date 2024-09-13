package com.prmto.kekodflashcard.domain

import com.prmto.kekodflashcard.data.repository.word.WordRepository
import com.prmto.kekodflashcard.domain.model.WordUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteWordsUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {

    operator fun invoke(): Flow<List<WordUI>> {
        return wordRepository.getFavoriteWords()
            .map {
                it.map {
                    it.toWordUI().copy(
                        isFavorite = true
                    )
                }
            }
    }
}