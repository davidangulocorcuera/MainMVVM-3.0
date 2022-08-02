package com.plexus.marvel.characterdetail

import android.app.Application
import androidx.fragment.app.testing.launchFragmentInContainer
import com.plexus.domain.Character
import com.plexus.domain.Image
import com.plexus.marvel.usescase.characterdetail.CharacterDetailFragment
import com.plexus.marvel.usescase.characterdetail.CharacterDetailViewModel
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


class CharacterDetailViewModelTest {

    private val viewModel: CharacterDetailViewModel by lazy {
        CharacterDetailViewModel(Application())
    }

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<CharacterDetailFragment>()
        scenario.onFragment { fragment ->
            fragment.apply {
                viewModel.onCharacterLoaded = ::onCharacterLoaded
                viewModel.onErrorLoadingCharacter = ::onErrorLoadingCharacter
            }
        }
    }

    @Test
    fun `character detail loaded should invoke onCharactersLoaded`() {
            Mockito.verify(viewModel.onCharacterLoaded).invoke(Character(
                id= 1,
                name = "example",
                description = "example description",
                thumbnail = Image(
                    path = "example path",
                    extension = "jpg"
                )
            ))
    }

    @Test
    fun `loading character error should invoke onErrorLoadingCharacters`() {
        Mockito.verify(viewModel.onErrorLoadingCharacter).invoke()
    }

}