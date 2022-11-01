package com.marvel.app.application

import android.content.Context
import androidx.room.Room
import com.plexus.data.storage.database.AppDatabase
import com.plexus.data.storage.database.LocalRepository
import com.plexus.data.storage.database.converters.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// DatabaseModule
@InstallIn(SingletonComponent::class)
@Module
class DataModule {
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
    fun provideLocalRepository(charactersDao: CharactersDao): LocalRepository =
        LocalRepository(charactersDao)
}
