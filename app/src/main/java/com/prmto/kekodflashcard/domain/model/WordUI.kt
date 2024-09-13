package com.prmto.kekodflashcard.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordUI(
    val id: Int,
    val englishWord: String,
    val turkishMean: String,
    val frenchMean: String,
    val imageUrl: String,
    val isFavorite: Boolean
) : Parcelable