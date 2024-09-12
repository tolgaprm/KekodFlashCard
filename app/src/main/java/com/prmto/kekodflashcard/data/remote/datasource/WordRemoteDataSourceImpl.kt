package com.prmto.kekodflashcard.data.remote.datasource

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import com.prmto.kekodflashcard.data.remote.service.WordService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRemoteDataSourceImpl @Inject constructor(
    private val wordService: WordService
) : BaseDataSource(), WordRemoteDataSource {
    override fun getWordList(): Flow<Response<List<WordResponse>>> {
        return apiCall { wordService.getWords() }
    }
}