package com.plexus.marvel.navigation


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Splash : Screen("splash")
    object Characters : Screen("characters")
    object CharacterDetail : Screen("profile/{userId}")
}