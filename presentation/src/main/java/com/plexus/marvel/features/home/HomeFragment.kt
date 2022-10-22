package com.plexus.marvel.features.home

import androidx.compose.runtime.Composable
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.components.CustomButton
import com.plexus.marvel.features.characters.CharactersFragment

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class.java) {
    @Composable
    override fun SetComposeView() {
        CustomButton(
            text = getString(R.string.go_to_characters_list),
            onClick = {
            navigator.navigate(CharactersFragment(), true)
        })
    }
}