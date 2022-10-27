package com.plexus.marvel.features.characterdetail

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import coil.compose.rememberAsyncImagePainter
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.components.CustomButton
import com.plexus.marvel.components.CustomProgressIndicator
import com.plexus.marvel.components.CustomTopAppBar
import com.plexus.marvel.features.characters.CharactersViewModel
import com.plexus.marvel.utils.Constants.Companion.EXTRA_CHARACTER_ID
import com.plexus.marvel.utils.getImageUrl

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class CharacterDetailFragment :
    BaseFragment() {

    var characterId = 0

    private val viewModel: CharacterDetailViewModel by viewModels()



    override fun viewCreated(view: View?) {
        characterId = arguments?.getInt(EXTRA_CHARACTER_ID) ?: 0
        viewModel.getCharacterDetail(characterId)
    }

    @Composable
    override fun SetComposeView() {
        val state by viewModel.characterState.collectAsState()
        when (state) {
            is CharacterDetailState.CharacterLoadedState -> {
                val current = (state as CharacterDetailState.CharacterLoadedState).character
                Column {
                    CustomTopAppBar(
                        title = current.name,
                        onBackClick = { navigator.goToLastFragment() })

                    Image(
                        painter = rememberAsyncImagePainter(current.getImageUrl()),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    )

                    Text(text = current.description, modifier = Modifier.padding(16.dp), fontSize = 14.sp)

                }
            }

            CharacterDetailState.ErrorLoadingCharacterState -> CustomButton(
                text = getString(R.string.splash_error_message),
                onClick = { viewModel.getCharacterDetail(characterId) })

            CharacterDetailState.Loading -> CustomProgressIndicator()
        }
    }

}