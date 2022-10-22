package com.plexus.marvel.features.characters

import com.plexus.domain.Character

sealed class CharactersState {
    data class CharactersLoadedState(val characters: List<Character>) : CharactersState()
    object ErrorLoadingCharactersState : CharactersState()
    object Loading : CharactersState()
}
