package com.plexus.marvel.usescase.characters

import com.plexus.domain.Character

sealed class CharactersState {
    data class CharactersLoadedState(val characters: ArrayList<Character>) : CharactersState()
    object ErrorLoadingCharactersState : CharactersState()
}
