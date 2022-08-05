package com.plexus.marvel.usescase.home

import com.plexus.domain.Character

sealed class SplashState {
    data class CharactersLoadedState(val characters: ArrayList<Character>) : SplashState()
    object ErrorLoadingCharactersState : SplashState()
}
