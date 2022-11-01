package com.mainapp.data.storage.database

import com.mainapp.data.storage.database.converters.CharacterDB
import com.mainapp.data.storage.database.converters.CharactersDao
import com.mainapp.domain.model.Character
import com.mainapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class LocalRepositoryImpl @Inject constructor(
    private val charactersDao: CharactersDao
) : LocalRepository {
    override suspend fun saveAllCharacters(characters: List<Character>) {
        characters.forEach {
            charactersDao.insertCharacter(
                CharacterDB(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    thumbnail = it.thumbnail
                )
            )
        }
    }

    override fun getAllCharacters(): List<Character> =
        charactersDao.getAllCharacters().map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnail = it.thumbnail
            )
        }
}
