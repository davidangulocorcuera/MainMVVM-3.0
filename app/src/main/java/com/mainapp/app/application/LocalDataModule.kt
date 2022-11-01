package com.mainapp.app.application

import android.content.Context
import androidx.room.Room
import com.mainapp.data.storage.database.AppDatabase
import com.mainapp.data.storage.database.LocalRepositoryImpl
import com.mainapp.data.storage.database.converters.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "marvel_database"
    ).build()

    @Singleton
    @Provides
    fun provideCharactersDao(db: AppDatabase) = db.charactersDao()

    @Singleton
    @Provides
    fun provideLocalRepository(charactersDao: CharactersDao): LocalRepositoryImpl =
        LocalRepositoryImpl(charactersDao)
}
