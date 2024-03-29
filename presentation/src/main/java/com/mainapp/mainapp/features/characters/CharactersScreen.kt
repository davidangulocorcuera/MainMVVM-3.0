package com.mainapp.mainapp.features.characters

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
import com.mainapp.domain.model.Character
import com.mainapp.mainapp.components.CustomButton
import com.mainapp.mainapp.components.CustomCardView
import com.mainapp.mainapp.components.CustomProgressIndicator
import com.mainapp.mainapp.components.CustomTopAppBar
import com.mainapp.mainapp.navigation.Constants.CHARACTER_ID
import com.mainapp.mainapp.navigation.Screen
import com.mainapp.mainapp.utils.getImageUrl
import com.mainapp.mainapp.R


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
            CharactersViewModel.CharactersState.ErrorLoadingCharactersState -> {
            }

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