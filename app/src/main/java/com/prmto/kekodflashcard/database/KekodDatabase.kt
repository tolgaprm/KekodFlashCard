package com.prmto.kekodflashcard.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prmto.kekodflashcard.data.local.dao.FavoriteWordDao
import com.prmto.kekodflashcard.data.local.dao.LearnedWordDao
import com.prmto.kekodflashcard.data.local.entity.FavoriteWordEntity
import com.prmto.kekodflashcard.data.local.entity.LearnedWordEntity

@Database(entities = [FavoriteWordEntity::class, LearnedWordEntity::class], version = 1)
abstract class KekodDatabase : RoomDatabase() {
    abstract fun favoriteWordDao(): FavoriteWordDao
    abstract fun learnedWordDao(): LearnedWordDao


    companion object {
        const val DATABASE_NAME = "kekod_database"
    }
}