package com.mainapp.domain.model

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Image? = null
)