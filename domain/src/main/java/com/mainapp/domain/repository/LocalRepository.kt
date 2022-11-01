package com.mainapp.domain.repository

import com.mainapp.domain.model.Character

interface LocalRepository {
    suspend fun saveAllCharacters(characters: List<Character>)
    fun getAllCharacters(): List<Character>
}