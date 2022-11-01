package com.plexus.domain.usecases

import com.plexus.domain.model.Character
import com.plexus.domain.repository.LocalRepository
import javax.inject.Inject

class SaveCharactersInDatabaseUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend fun saveAllCharacters(characters: List<Character>) = localRepository.saveAllCharacters(characters)
}