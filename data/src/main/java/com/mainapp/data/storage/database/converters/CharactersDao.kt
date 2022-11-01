package com.mainapp.data.storage.database.converters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@Dao
interface CharactersDao {
    @Query("select * from characters")
    fun getAllCharacters(): List<CharacterDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterDB)
}