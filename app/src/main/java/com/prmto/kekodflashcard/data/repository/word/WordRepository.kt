package com.prmto.kekodflashcard.data.repository.word

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.data.local.entity.FavoriteWordEntity
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import com.prmto.kekodflashcard.domain.model.WordUI
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getWords(): Flow<Response<List<WordResponse>>>

    suspend fun insertFavoriteWord(word: WordUI)

    suspend fun deleteFavoriteWord(word: WordUI)

    fun getFavoriteWords(): Flow<List<FavoriteWordEntity>>
}