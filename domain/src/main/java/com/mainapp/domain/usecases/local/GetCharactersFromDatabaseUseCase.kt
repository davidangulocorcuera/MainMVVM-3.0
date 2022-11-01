package com.mainapp.domain.usecases.local

import com.mainapp.domain.model.Character
import com.mainapp.domain.repository.LocalRepository
import javax.inject.Inject

class GetCharactersFromDatabaseUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    fun getAllCharacters(): List<Character> = localRepository.getAllCharacters()
}