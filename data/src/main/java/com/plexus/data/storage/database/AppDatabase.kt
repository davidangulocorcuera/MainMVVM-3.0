package com.plexus.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plexus.data.storage.database.converters.CharacterDB
import com.plexus.data.storage.database.converters.CharactersDao
import com.plexus.data.storage.database.converters.Converters

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@Database(entities = [CharacterDB::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}