package com.mainapp.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mainapp.data.storage.database.converters.CharacterDB
import com.mainapp.data.storage.database.converters.CharactersDao
import com.mainapp.data.storage.database.converters.Converters

/**
 * © Class created by David Angulo
 * */

@Database(entities = [CharacterDB::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}