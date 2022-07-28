package com.plexus.data.storage.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plexus.domain.Character

class Converters {
    @TypeConverter
    fun fromCharacter(character: Character?): String? {
        if (character == null) return null
        val gson = Gson()
        val type = object : TypeToken<Character>() {}.type
        return gson.toJson(character, type)
    }
    @TypeConverter
    fun toCharacter(character: String?): Character? {
        if (character == null) return null
        val gson = Gson()
        val type = object : TypeToken<Character>() {}.type
        return gson.fromJson<Character>(character, type)
    }

    @TypeConverter
    fun fromCharacters(characters: List<CharacterDB>?): String? {
        if (characters == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<CharacterDB>>() {}.type
        return gson.toJson(characters, type)
    }
    @TypeConverter
    fun toCharacters(characters: String?): List<CharacterDB>? {
        if (characters == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Character>>() {}.type
        return gson.fromJson<List<CharacterDB>>(characters, type)
    }
}