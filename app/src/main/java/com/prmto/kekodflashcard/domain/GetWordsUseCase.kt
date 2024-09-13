package com.prmto.kekodflashcard.domain

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.common.map
import com.prmto.kekodflashcard.data.repository.word.WordRepository
import com.prmto.kekodflashcard.domain.model.WordUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetWordsUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {

    operator fun invoke(): Flow<Response<List<WordUI>>> {
        return combine(
            wordRepository.getWords(),
            wordRepository.getFavoriteWords(),
            wordRepository.getLearnedWords()
        ) { response, favoriteWords, learnedWords ->
            response.map { listOfWords ->
                listOfWords.filterNot { it.id in learnedWords.map { it.id } }.map {
                    it.toWordUI()
                        .copy(
                            englishWord = it.englishWord.replaceFirstChar { it.uppercase() },
                            turkishMean = it.turkishMean.replaceFirstChar { it.uppercase() },
                            frenchMean = it.frenchMean.replaceFirstChar { it.uppercase() },
                            isFavorite = it.id in favoriteWords.map { it.id }
                        )
                }
            }
        }
    }
}
