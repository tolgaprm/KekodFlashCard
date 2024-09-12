package com.prmto.kekodflashcard.di

import com.prmto.kekodflashcard.data.local.PreferencesRepository
import com.prmto.kekodflashcard.data.local.PreferencesRepositoryImpl
import com.prmto.kekodflashcard.data.remote.datasource.WordRemoteDataSource
import com.prmto.kekodflashcard.data.remote.datasource.WordRemoteDataSourceImpl
import com.prmto.kekodflashcard.data.remote.repository.WordRepository
import com.prmto.kekodflashcard.data.remote.repository.WordRepositoryImpl
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