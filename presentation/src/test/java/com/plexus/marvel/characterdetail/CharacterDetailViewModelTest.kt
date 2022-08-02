package com.plexus.marvel.characterdetail

import androidx.fragment.app.testing.launchFragmentInContainer
import com.plexus.domain.Character
import com.plexus.domain.Image
import com.plexus.marvel.usescase.characterdetail.CharacterDetailFragment
import com.plexus.marvel.usescase.characterdetail.CharacterDetailViewModel
import org.junit.Test
import org.junit.Before
import org.mockito.Mockito


class CharacterDetailViewModelTest {

    private val viewModel: CharacterDetailViewModel by lazy {
        Mockito.mock(CharacterDetailViewModel::class.java)
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