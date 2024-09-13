package com.prmto.kekodflashcard.data.remote.datasource

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import kotlinx.coroutines.flow.Flow

interface WordRemoteDataSource {

    fun getWordList(): Flow<Response<List<WordResponse>>>
}