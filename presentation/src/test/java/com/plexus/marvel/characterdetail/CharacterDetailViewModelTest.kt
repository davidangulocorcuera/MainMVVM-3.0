package com.plexus.marvel.characterdetail

import com.plexus.marvel.features.characterdetail.CharacterDetailViewModel
import org.mockito.Mockito


class CharacterDetailViewModelTest {

    private val viewModel: CharacterDetailViewModel by lazy {
        Mockito.mock(CharacterDetailViewModel::class.java)
    }

}