package com.mainapp.app.application

import com.mainapp.data.cloud.repository.ServicesRepositoryImpl
import com.mainapp.data.storage.database.LocalRepositoryImpl
import com.mainapp.domain.usecases.network.GetAllCharactersUseCase
import com.mainapp.domain.usecases.network.GetCharacterDetailUseCase
import com.mainapp.domain.usecases.local.GetCharactersFromDatabaseUseCase
import com.mainapp.domain.usecases.local.SaveCharactersInDatabaseUseCase
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