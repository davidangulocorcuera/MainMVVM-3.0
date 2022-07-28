package com.plexus.data.storage.database.converters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plexus.domain.Character

@Dao
interface CharactersDao {
    @Query("select * from characters")
    fun getAllCharacters(): List<CharacterDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterDB)
}