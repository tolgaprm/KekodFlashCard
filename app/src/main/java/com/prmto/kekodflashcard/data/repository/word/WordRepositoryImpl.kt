package com.prmto.kekodflashcard.data.repository.word

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.data.local.dao.FavoriteWordDao
import com.prmto.kekodflashcard.data.local.dao.LearnedWordDao
import com.prmto.kekodflashcard.data.local.entity.FavoriteWordEntity
import com.prmto.kekodflashcard.data.local.entity.LearnedWordEntity
import com.prmto.kekodflashcard.data.remote.datasource.WordRemoteDataSource
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import com.prmto.kekodflashcard.domain.model.WordUI
import com.prmto.kekodflashcard.domain.toWordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordRemoteDataSource: WordRemoteDataSource,
    private val favoriteWordDao: FavoriteWordDao,
    private val learnedWordDao: LearnedWordDao
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

    override fun getLearnedWords(): Flow<List<LearnedWordEntity>> {
        return learnedWordDao.getLearnedWords()
    }

    override suspend fun insertLearnedWord(word: LearnedWordEntity) {
        learnedWordDao.insertLearnedWord(word)
    }

    override suspend fun deleteLearnedWord(word: LearnedWordEntity) {
        learnedWordDao.deleteLearnedWord(word)
    }
}