package com.plexus.marvel.features.characters

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.plexus.marvel.R
import com.plexus.domain.Character
import com.plexus.marvel.components.CustomButton
import com.plexus.marvel.components.CustomCardView
import com.plexus.marvel.components.CustomProgressIndicator
import com.plexus.marvel.components.CustomTopAppBar
import com.plexus.marvel.navigation.Constants.CHARACTER_ID
import com.plexus.marvel.navigation.Screen
import com.plexus.marvel.utils.getImageUrl


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.getAllCharactersFromDatabase()
    }
    val offset = viewModel.offset.observeAsState(initial = 0)
    val state by viewModel.charactersState.collectAsState()
    Box(Modifier.fillMaxSize()) {
        Column {
            CustomTopAppBar(
                title = stringResource(R.string.characters_toolbar_title),
                onBackClick = {
                    navController.popBackStack()
                },
                endActions = listOf { RefreshAction { viewModel.reloadCharacters() } }
            )
            CharactersList(
                items = viewModel.characters,
                navController = navController,
                onEndOfScroll = {
                    viewModel.getAllCharacters(offset.value)
                })
        }
        when (state) {
            is CharactersViewModel.CharactersState.CharactersLoadedState -> offset.value.plus(1)
            CharactersViewModel.CharactersState.ErrorLoadingCharactersState ->
                CustomButton(
                    text = stringResource(R.string.splash_error_message),
                    onClick = { viewModel.getAllCharacters(offset.value) })

            CharactersViewModel.CharactersState.Loading -> CustomProgressIndicator(
                modifier = Modifier.align(
                    Center
                )
            )
        }

    }
}

@Composable
private fun RefreshAction(onClick: () -> Unit) {
    IconButton(onClick = {
        onClick.invoke()
    }) {
        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = "Reload"
        )
    }
}

@Composable
private fun CharactersList(
    items: List<Character>,
    onEndOfScroll: () -> Unit,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        Modifier.scrollable(rememberScrollState(), Orientation.Vertical),
        state = rememberLazyGridState()
    ) {
        itemsIndexed(items) { index, item ->
            CustomCardView(
                modifier = Modifier.size(150.dp),
                onClick = {
                    navController.navigate(
                        Screen.CharacterDetail.route.replace(
                            oldValue = "{$CHARACTER_ID}",
                            newValue = "${item.id}"
                        )
                    )
                },
                image = item.getImageUrl(),
                cornerRadius = 8.dp,
                elevation = 8.dp
            )
            if (items.lastIndex == index) {
                onEndOfScroll.invoke()
            }
        }
    }
}