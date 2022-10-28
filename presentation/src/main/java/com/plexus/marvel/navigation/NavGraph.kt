package com.plexus.marvel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plexus.marvel.features.characterdetail.CharacterDetailScreen
import com.plexus.marvel.features.characters.CharactersScreen
import com.plexus.marvel.features.home.HomeScreen
import com.plexus.marvel.features.splash.SplashScreen
import com.plexus.marvel.navigation.Constants.USER_ID

@Composable
fun CustomNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Characters.route) {
            CharactersScreen(navController = navController)
        }
        composable(
            Screen.CharacterDetail.route,
            arguments = listOf(navArgument(USER_ID) { type = NavType.IntType })
        ) { CharacterDetailScreen(navController = navController) }
    }
}

