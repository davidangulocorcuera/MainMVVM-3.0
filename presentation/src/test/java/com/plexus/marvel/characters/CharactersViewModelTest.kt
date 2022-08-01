package com.plexus.marvel.characters

import android.app.Application
import androidx.fragment.app.testing.launchFragmentInContainer
import com.plexus.marvel.usescase.characters.CharactersFragment
import com.plexus.marvel.usescase.characters.CharactersViewModel
import com.plexus.marvel.usescase.home.HomeFragment
import com.plexus.marvel.usescase.home.HomeViewModel
import com.plexus.marvel.usescase.splash.SplashFragment
import com.plexus.marvel.usescase.splash.SplashViewModel
import dagger.android.AndroidInjection.inject
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito


class CharactersViewModelTest {

    private val viewModel: CharactersViewModel by lazy {
        CharactersViewModel(Application())
    }

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<CharactersFragment>()
        scenario.onFragment { fragment ->
            fragment.apply {
                viewModel.onCharactersLoaded = ::onCharactersLoaded
                viewModel.onErrorLoadingCharacters = ::onErrorLoadingCharacters
                viewModel.goToCharacterDetail = ::goToCharacterDetail
            }
        }
    }

    @Test
    fun `characters loaded should invoke onCharactersLoaded`() {
        Mockito.verify(viewModel.onCharactersLoaded).invoke(ArrayList())
    }

    @Test
    fun `characters error should invoke onErrorLoadingCharacters`() {
        Mockito.verify(viewModel.onErrorLoadingCharacters).invoke()
    }

    @Test
    fun `user navigate to character detail`() {
        Mockito.verify(viewModel.goToCharacterDetail).invoke(1)
    }

}