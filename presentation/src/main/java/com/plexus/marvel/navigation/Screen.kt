package com.plexus.marvel.navigation

import com.plexus.marvel.navigation.Constants.CHARACTER_ID


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Splash : Screen("splash")
    object Characters : Screen("characters")
    object CharacterDetail : Screen("characterDetail/{$CHARACTER_ID}")
}