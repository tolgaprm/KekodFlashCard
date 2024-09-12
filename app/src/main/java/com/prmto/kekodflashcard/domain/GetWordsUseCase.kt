package com.prmto.kekodflashcard.domain

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.common.map
import com.prmto.kekodflashcard.data.remote.repository.WordRepository
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWordsUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {

    operator fun invoke(): Flow<Response<List<WordResponse>>> {
        return wordRepository.getWords()
            .map { response ->
                response.map { listOfWords ->
                    listOfWords.shuffled().map {
                        it.copy(
                            englishWord = it.englishWord.replaceFirstChar { it.uppercase() },
                            turkishMean = it.turkishMean.replaceFirstChar { it.uppercase() },
                            frenchMean = it.frenchMean.replaceFirstChar { it.uppercase() },
                        )
                    }
                }
            }
    }
}
