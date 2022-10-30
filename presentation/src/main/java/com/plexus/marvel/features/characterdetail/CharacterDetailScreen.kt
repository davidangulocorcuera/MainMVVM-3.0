package com.plexus.marvel.features.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.plexus.marvel.R
import com.plexus.marvel.components.CustomButton
import com.plexus.marvel.components.CustomProgressIndicator
import com.plexus.marvel.components.CustomTopAppBar
import com.plexus.marvel.utils.getImageUrl


@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.getCharacterDetail(characterId)
    }

    val state by viewModel.characterState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {

        when (state) {
            is CharacterDetailViewModel.CharacterDetailState.CharacterLoadedState -> {
                val current =
                    (state as CharacterDetailViewModel.CharacterDetailState.CharacterLoadedState).character

                Column(Modifier.fillMaxSize()) {
                    CustomTopAppBar(
                        title = current.name,
                        onBackClick = {
                            navController.popBackStack()
                        })

                    Image(
                        painter = rememberAsyncImagePainter(current.getImageUrl()),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    )

                    Text(
                        text = current.description,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 14.sp
                    )
                }
            }

            CharacterDetailViewModel.CharacterDetailState.ErrorLoadingCharacterState -> CustomButton(
                text = stringResource(R.string.splash_error_message),
                onClick = {
                    viewModel.getCharacterDetail(characterId)
                })

            CharacterDetailViewModel.CharacterDetailState.Loading -> CustomProgressIndicator(
                modifier = Modifier.align(
                    Center
                )
            )
        }
    }

}
