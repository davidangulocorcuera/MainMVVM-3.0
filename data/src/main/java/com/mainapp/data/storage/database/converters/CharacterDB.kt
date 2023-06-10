package com.mainapp.data.storage.database.converters

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mainapp.domain.model.Image

/**
 * © Class created by David Angulo
 * */


@Entity(tableName = "characters")
data class CharacterDB(
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "thumbnail") var thumbnail: Image? = null
)