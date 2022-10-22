package com.plexus.marvel.features.characters

sealed class CharactersInstructions {
    data class CharacterClickedState(val id: Int) : CharactersInstructions()
}