package com.marvel.app.application

import com.plexus.data.cloud.repository.ServicesRepositoryImpl
import com.plexus.data.storage.database.LocalRepositoryImpl
import com.plexus.domain.usecases.GetAllCharactersUseCase
import com.plexus.domain.usecases.GetCharacterDetailUseCase
import com.plexus.domain.usecases.GetCharactersFromDatabaseUseCase
import com.plexus.domain.usecases.SaveCharactersInDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideGetAllCharactersUseCase(servicesRepositoryImpl: ServicesRepositoryImpl): GetAllCharactersUseCase =
        GetAllCharactersUseCase(servicesRepositoryImpl)

    @Singleton
    @Provides
    fun provideGetCharacterDetailUseCase(servicesRepositoryImpl: ServicesRepositoryImpl): GetCharacterDetailUseCase =
        GetCharacterDetailUseCase(servicesRepositoryImpl)

    @Singleton
    @Provides
    fun provideGetCharactersFromDatabaseUseCase(localRepositoryImpl: LocalRepositoryImpl): GetCharactersFromDatabaseUseCase =
        GetCharactersFromDatabaseUseCase(localRepositoryImpl)

    @Singleton
    @Provides
    fun provideSaveCharactersInDatabaseUseCase(localRepositoryImpl: LocalRepositoryImpl): SaveCharactersInDatabaseUseCase =
        SaveCharactersInDatabaseUseCase(localRepositoryImpl)
}