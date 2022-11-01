package com.plexus.domain.repository

import com.plexus.domain.model.Character
import com.plexus.domain.model.response.BaseResponse
import com.plexus.domain.model.response.CharactersResponse
import io.reactivex.Single

interface LocalRepository {
    suspend fun saveAllCharacters(characters: List<Character>)
    fun getAllCharacters(): List<Character>
}