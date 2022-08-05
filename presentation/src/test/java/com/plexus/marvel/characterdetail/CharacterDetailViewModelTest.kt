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

}