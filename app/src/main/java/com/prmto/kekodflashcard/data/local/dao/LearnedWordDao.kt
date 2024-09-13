package com.prmto.kekodflashcard.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prmto.kekodflashcard.data.local.entity.LearnedWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LearnedWordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLearnedWord(learnedWordEntity: LearnedWordEntity)

    @Delete
    suspend fun deleteLearnedWord(learnedWordEntity: LearnedWordEntity)

    @Query("SELECT * FROM learnedwordentity")
    fun getLearnedWords(): Flow<List<LearnedWordEntity>>
}