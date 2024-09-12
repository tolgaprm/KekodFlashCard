package com.prmto.kekodflashcard.data.remote.response

import com.google.gson.annotations.SerializedName

data class WordResponse(
    val id: Int,
    @SerializedName("english_word") val englishWord: String,
    @SerializedName("turkish_mean") val turkishMean: String,
    @SerializedName("french_mean") val frenchMean: String,
    @SerializedName("image_url") val imageUrl: String
)
