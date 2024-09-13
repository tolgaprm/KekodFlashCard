package com.prmto.kekodflashcard.di

import android.content.Context
import androidx.room.Room
import com.prmto.kekodflashcard.database.KekodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): KekodDatabase {
        return Room.databaseBuilder(
            context,
            KekodDatabase::class.java,
            KekodDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteWordDao(
        database: KekodDatabase
    ) = database.favoriteWordDao()

    @Provides
    @Singleton
    fun provideLearnedWordDao(
        database: KekodDatabase
    ) = database.learnedWordDao()
}