package com.plexus.data.storage.database.converters

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterDB(
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "image") var image: String? = null
)