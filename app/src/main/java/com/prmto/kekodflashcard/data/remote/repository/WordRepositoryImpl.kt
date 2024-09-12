package com.prmto.kekodflashcard.data.remote.repository

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.data.remote.datasource.WordRemoteDataSource
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordRemoteDataSource: WordRemoteDataSource
) : WordRepository {
    override fun getWords(): Flow<Response<List<WordResponse>>> {
        return wordRemoteDataSource.getWordList()
    }
}