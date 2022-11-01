package com.mainapp.mainapp.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.mainapp.mainapp.components.CustomButton
import com.mainapp.mainapp.navigation.Screen


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Box(Modifier.fillMaxSize()) {
        CustomButton(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.go_to_characters_list),
            onClick = {
                navController.navigate(Screen.Characters.route)
            })
    }
}