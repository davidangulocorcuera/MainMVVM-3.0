package com.plexus.marvel.features.characters

import android.os.Bundle
import android.view.View
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.components.CustomButton
import com.plexus.marvel.components.CustomCardView
import com.plexus.marvel.components.CustomProgressIndicator
import com.plexus.marvel.components.CustomTopAppBar
import com.plexus.marvel.features.characterdetail.CharacterDetailFragment
import com.plexus.marvel.features.home.HomeViewModel
import com.plexus.marvel.utils.Constants.Companion.EXTRA_CHARACTER_ID
import com.plexus.marvel.utils.getImageUrl

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class CharactersFragment : BaseFragment() {

    private var offset = 0

    private val viewModel: CharactersViewModel by viewModels()


    override fun viewCreated(view: View?) {
        if (viewModel.characters.isEmpty()) viewModel.getAllCharactersFromDatabase()
    }

    private fun goToCharacterDetail(id: Int) {
        navigator.navigate(CharacterDetailFragment(), true, Bundle().apply {
            putInt(EXTRA_CHARACTER_ID, id)
        })
    }

    @Composable
    override fun SetComposeView() {
        val state by viewModel.charactersState.collectAsState()
        Column {
            CustomTopAppBar(
                title = stringResource(R.string.characters_toolbar_title),
                onBackClick = { navigator.goToLastFragment() },
                endActions = listOf { RefreshAction() }
            )
            CharactersList()
        }

        when (state) {
            is CharactersState.CharactersLoadedState -> offset++
            CharactersState.ErrorLoadingCharactersState ->
                CustomButton(
                    text = getString(R.string.splash_error_message),
                    onClick = { viewModel.getAllCharacters(offset) })

            CharactersState.Loading -> CustomProgressIndicator()
        }
    }

    @Composable
    private fun RefreshAction() {
        IconButton(onClick = {
            viewModel.reloadCharacters()
        }) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Reload"
            )
        }
    }

    @Composable
    private fun CharactersList() {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            Modifier.scrollable(rememberScrollState(), Orientation.Vertical),
            state = rememberLazyGridState()
        ) {
            itemsIndexed(viewModel.characters) { index, item ->
                CustomCardView(
                    modifier = Modifier.size(150.dp),
                    onClick = { goToCharacterDetail(item.id) },
                    image = item.getImageUrl(),
                    cornerRadius = 8.dp,
                    elevation = 8.dp
                )
                if (viewModel.characters.lastIndex == index) {
                    viewModel.getAllCharacters(offset)
                }
            }
        }
    }

}