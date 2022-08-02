package com.plexus.marvel.characters

import androidx.fragment.app.testing.launchFragmentInContainer
import com.plexus.domain.Character
import com.plexus.domain.Image
import com.plexus.marvel.usescase.characters.CharactersFragment
import com.plexus.marvel.usescase.characters.CharactersViewModel
import org.junit.Test
import org.junit.Before
import org.mockito.Mockito


class CharactersViewModelTest {

    private val viewModel: CharactersViewModel by lazy {
        Mockito.mock(CharactersViewModel::class.java)
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
        Mockito.verify(viewModel.onCharactersLoaded).invoke(
            arrayListOf(
                Character(
                    id = 1,
                    name = "example",
                    description = "example description",
                    thumbnail = Image(
                        path = "example path",
                        extension = "jpg"
                    )
                )
            )
        )
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