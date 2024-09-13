package com.prmto.kekodflashcard.domain

import com.prmto.kekodflashcard.domain.model.WordUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUseCase @Inject constructor() {

    suspend fun search(
        query: String,
        wordList: List<WordUI>
    ): List<WordUI> {
        return withContext(Dispatchers.Default) {
            wordList.filter {
                wordMatchesQuery(it, query)
            }
        }
    }

    private fun wordMatchesQuery(word: WordUI, query: String): Boolean {
        return word.englishWord.contains(query, ignoreCase = true) ||
                word.turkishMean.contains(query, ignoreCase = true) ||
                word.frenchMean.contains(query, ignoreCase = true)
    }
}