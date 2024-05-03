package com.mainapp.domain.model

class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Image? = null
)