package com.prmto.kekodflashcard.data.remote.repository

import com.prmto.kekodflashcard.common.Response
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getWords(): Flow<Response<List<WordResponse>>>
}