package com.plexus.data.storage.database.converters

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plexus.domain.Image

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */


@Entity(tableName = "characters")
data class CharacterDB(
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "thumbnail") var thumbnail: Image? = null
)