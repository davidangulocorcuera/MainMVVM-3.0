package com.mainapp.mainapp.navigation

import com.mainapp.mainapp.navigation.Constants.CHARACTER_ID


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Splash : Screen("splash")
    object Characters : Screen("characters")
    object CharacterDetail : Screen("characterDetail/{$CHARACTER_ID}")
}