package com.plexus.marvel.usescase.characterdetail

import com.plexus.domain.Character

sealed class CharacterDetailState {
    data class CharacterLoadedState(val character: Character) : CharacterDetailState()
    object ErrorLoadingCharacterState : CharacterDetailState()
}
