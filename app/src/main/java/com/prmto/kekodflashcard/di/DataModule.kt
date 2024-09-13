package com.prmto.kekodflashcard.di

import com.prmto.kekodflashcard.data.remote.datasource.WordRemoteDataSource
import com.prmto.kekodflashcard.data.remote.datasource.WordRemoteDataSourceImpl
import com.prmto.kekodflashcard.data.repository.preferences.PreferencesRepository
import com.prmto.kekodflashcard.data.repository.preferences.PreferencesRepositoryImpl
import com.prmto.kekodflashcard.data.repository.word.WordRepository
import com.prmto.kekodflashcard.data.repository.word.WordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun providePreferencesRepository(
        preferencesRepositoryImpl: PreferencesRepositoryImpl
    ): PreferencesRepository

    @Binds
    @Singleton
    fun provideWordRemoteDataSource(
        wordRemoteDataSourceImpl: WordRemoteDataSourceImpl
    ): WordRemoteDataSource

    @Binds
    @Singleton
    fun provideWordRepository(
        wordRepositoryImpl: WordRepositoryImpl
    ): WordRepository
}