package com.plexus.marvel.features.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.plexus.marvel.R
import com.plexus.marvel.components.CustomButton
import com.plexus.marvel.components.CustomLoading


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.splashState.collectAsState()

    // called once every time this composable enters the composition
    LaunchedEffect(Unit) {
        viewModel.getAllCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is SplashState.CharactersLoadedState -> {
                //navigate
            }
            SplashState.ErrorLoadingCharactersState -> CustomButton(
                text = stringResource(R.string.splash_error_message),
                onClick = { viewModel.getAllCharacters() })
            SplashState.Loading -> CustomLoading(anim = R.raw.anim_loading)
        }
    }
}