package com.prmto.kekodflashcard.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LearnedWordEntity(
    @PrimaryKey val id: Int,
    val englishWord: String,
    val turkishWord: String,
    val frenchWord: String,
    val imageUrl: String,
)
