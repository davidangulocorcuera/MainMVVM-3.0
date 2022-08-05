package com.plexus.marvel.usescase.characters

sealed class CharactersInstructions {
    data class CharacterClickedState(val id: Int) : CharactersInstructions()
}