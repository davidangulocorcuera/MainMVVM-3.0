package com.plexus.data.storage.database

import com.plexus.data.storage.database.converters.CharacterDB
import com.plexus.domain.Character

class LocalRepository {
    fun saveAllCharacters(characters: ArrayList<Character>, db: AppDatabase) {
        characters.forEach {
            db.charactersDao().insertCharacter(
                CharacterDB(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    image = it.image
                )
            )
        }
    }

    fun getAllCharacters(db: AppDatabase): ArrayList<Character> {
        val characters = ArrayList<Character>()
        db.charactersDao().getAllCharacters().forEach {
            characters.add(
                Character(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    image = it.image
                )
            )
        }
        return characters
    }
}