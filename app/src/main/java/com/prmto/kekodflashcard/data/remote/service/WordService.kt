package com.prmto.kekodflashcard.data.remote.service

import com.prmto.kekodflashcard.data.remote.response.WordResponse
import retrofit2.http.GET

interface WordService {

    @GET("word.json")
   suspend fun getWords(): List<WordResponse>
}