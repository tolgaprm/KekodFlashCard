package com.prmto.kekodflashcard.data.repository.word

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.data.local.dao.FavoriteWordDao
import com.prmto.kekodflashcard.data.local.entity.FavoriteWordEntity
import com.prmto.kekodflashcard.data.remote.datasource.WordRemoteDataSource
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import com.prmto.kekodflashcard.domain.model.WordUI
import com.prmto.kekodflashcard.domain.toWordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordRemoteDataSource: WordRemoteDataSource,
    private val favoriteWordDao: FavoriteWordDao
) : WordRepository {
    override fun getWords(): Flow<Response<List<WordResponse>>> {
        return wordRemoteDataSource.getWordList()
    }

    override suspend fun insertFavoriteWord(word: WordUI) {
        favoriteWordDao.insertWord(word.toWordEntity())
    }

    override suspend fun deleteFavoriteWord(word: WordUI) {
        favoriteWordDao.deleteWord(word.toWordEntity())
    }

    override fun getFavoriteWords(): Flow<List<FavoriteWordEntity>> {
        return favoriteWordDao.getFavoriteWords()
    }
}