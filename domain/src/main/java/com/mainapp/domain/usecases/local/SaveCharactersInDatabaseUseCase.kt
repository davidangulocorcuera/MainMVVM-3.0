package com.mainapp.domain.usecases.local

import com.mainapp.domain.model.Character
import com.mainapp.domain.repository.LocalRepository
import javax.inject.Inject

class SaveCharactersInDatabaseUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend fun saveAllCharacters(characters: List<Character>) = localRepository.saveAllCharacters(characters)
}