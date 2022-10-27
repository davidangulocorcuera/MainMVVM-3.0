package com.plexus.marvel.features.splash

import com.plexus.domain.Character

sealed class SplashState {
    object CharactersLoadedState : SplashState()
    object ErrorLoadingCharactersState : SplashState()
    object Loading : SplashState()
}
