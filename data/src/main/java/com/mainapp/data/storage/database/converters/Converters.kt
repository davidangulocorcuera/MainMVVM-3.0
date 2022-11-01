package com.mainapp.data.storage.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mainapp.domain.model.Image

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class Converters {
    @TypeConverter
    fun fromImage(image: Image?): String? {
        if (image == null) return null
        val gson = Gson()
        val type = object : TypeToken<Image>() {}.type
        return gson.toJson(image, type)
    }

    @TypeConverter
    fun toImage(image: String?): Image? {
        if (image == null) return null
        val gson = Gson()
        val type = object : TypeToken<Image>() {}.type
        return gson.fromJson<Image>(image, type)
    }
}