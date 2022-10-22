package com.plexus.marvel.features.splash

import com.plexus.domain.Character

sealed class SplashState {
    data class CharactersLoadedState(val characters: ArrayList<Character>) : SplashState()
    object ErrorLoadingCharactersState : SplashState()
    object Loading : SplashState()
}
