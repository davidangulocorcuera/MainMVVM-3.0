package com.plexus.marvel.utils
import com.plexus.domain.model.Character

/**
 * © File created by David Angulo , david.angulocorcuera@plexus.es
 * */

fun Character.getImageUrl(): String{
    return thumbnail?.path?.replace(
        "http",
        "https"
    ) + "." + thumbnail?.extension
}