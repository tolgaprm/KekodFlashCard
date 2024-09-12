package com.prmto.kekodflashcard.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prmto.kekodflashcard.data.local.dao.FavoriteWordDao
import com.prmto.kekodflashcard.data.local.entity.FavoriteWordEntity

@Database(entities = [FavoriteWordEntity::class], version = 1)
abstract class KekodDatabase : RoomDatabase() {
    abstract fun favoriteWordDao(): FavoriteWordDao


    companion object {
        const val DATABASE_NAME = "kekod_database"
    }
}