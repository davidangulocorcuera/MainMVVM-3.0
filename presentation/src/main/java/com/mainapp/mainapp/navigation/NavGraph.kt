package com.mainapp.mainapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mainapp.mainapp.features.characterdetail.CharacterDetailScreen
import com.mainapp.mainapp.features.characters.CharactersScreen
import com.mainapp.mainapp.features.home.HomeScreen
import com.mainapp.mainapp.features.splash.SplashScreen
import com.mainapp.mainapp.navigation.Constants.CHARACTER_ID

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
            arguments = listOf(navArgument(CHARACTER_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            CharacterDetailScreen(
                navController = navController,
                characterId = backStackEntry.arguments?.getInt(CHARACTER_ID) ?: 0
            )
        }
    }
}

