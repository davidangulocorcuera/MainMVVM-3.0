package com.mainapp.mainapp.features.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mainapp.mainapp.components.CustomButton
import com.mainapp.mainapp.components.CustomLoading
import com.mainapp.mainapp.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.splashState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllCharacters()
        viewModel.splashNavigation.onEach {
            when (it) {
                is SplashViewModel.SplashNavigation.NavigateToHome -> navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }.launchIn(this)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {

        when (state) {
            SplashViewModel.SplashState.ErrorLoadingCharactersState -> CustomButton(
                text = stringResource(R.string.splash_error_message),
                onClick = { viewModel.getAllCharacters() })
            SplashViewModel.SplashState.Loading -> CustomLoading(anim = R.raw.anim_loading)
        }
    }
}
