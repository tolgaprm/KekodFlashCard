package com.prmto.kekodflashcard.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prmto.kekodflashcard.data.local.entity.FavoriteWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteWordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(entity: FavoriteWordEntity)

    @Delete
    suspend fun deleteWord(entity: FavoriteWordEntity)

    @Query("SELECT * FROM FavoriteWordEntity")
    fun getFavoriteWords(): Flow<List<FavoriteWordEntity>>
}