package com.plexus.marvel.features.splash

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.plexus.data.storage.database.LocalRepository
import com.plexus.domain.Character
import com.plexus.marvel.R
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.components.CustomButton
import com.plexus.marvel.components.CustomLoading

import com.plexus.marvel.features.home.HomeFragment
import kotlinx.coroutines.*

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class SplashFragment :
    BaseFragment<SplashViewModel>(SplashViewModel::class.java) {

    override fun viewCreated(view: View?) {
        viewModel.getAllCharacters()
    }

    private fun onCharactersLoaded(characters: ArrayList<Character>) {

        CoroutineScope(Job()).launch {
            LocalRepository().saveAllCharacters(characters, App().getDatabase(requireContext()))
            withContext(Dispatchers.Main) {
                navigator.navigate(HomeFragment())
            }
        }
    }

    @Composable
    override fun SetComposeView() {
        val state by viewModel.splashState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            when (state) {
                is SplashState.CharactersLoadedState -> onCharactersLoaded((state as SplashState.CharactersLoadedState).characters)
                SplashState.ErrorLoadingCharactersState -> CustomButton(
                    text = getString(R.string.splash_error_message),
                    onClick = { viewModel.getAllCharacters() })
                SplashState.Loading -> CustomLoading(anim = R.raw.anim_loading)
            }
        }
    }

}