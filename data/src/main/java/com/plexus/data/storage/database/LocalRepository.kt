package com.plexus.data.storage.database

import com.plexus.data.storage.database.converters.CharacterDB
import com.plexus.data.storage.database.converters.CharactersDao
import com.plexus.domain.Character
import javax.inject.Inject

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class LocalRepository @Inject constructor(
    private val charactersDao: CharactersDao
) {
  suspend fun saveAllCharacters(characters: List<Character>) {
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

    fun getAllCharacters(): List<Character> =
        charactersDao.getAllCharacters().map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnail = it.thumbnail
            )
        }
}
