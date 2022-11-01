package com.plexus.domain.usecases

import com.plexus.domain.model.Character
import com.plexus.domain.repository.LocalRepository
import javax.inject.Inject

class GetCharactersFromDatabaseUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    fun getAllCharacters(): List<Character> = localRepository.getAllCharacters()
}