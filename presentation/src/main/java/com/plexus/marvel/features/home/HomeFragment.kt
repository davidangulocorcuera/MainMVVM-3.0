package com.plexus.marvel.features.home

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.components.CustomButton
import com.plexus.marvel.features.characters.CharactersFragment
import com.plexus.marvel.features.splash.SplashViewModel

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    @Composable
    override fun SetComposeView() {
        CustomButton(
            text = getString(R.string.go_to_characters_list),
            onClick = {
            navigator.navigate(CharactersFragment(), true)
        })
    }
}