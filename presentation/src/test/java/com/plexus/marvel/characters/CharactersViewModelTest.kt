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