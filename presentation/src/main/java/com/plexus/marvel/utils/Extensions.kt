package com.plexus.marvel.utils
import com.plexus.domain.Character

fun Character.getImageUrl(): String{
    return thumbnail?.path?.replace(
        "http",
        "https"
    ) + "." + thumbnail?.extension
}