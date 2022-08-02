package com.plexus.marvel.home

import androidx.fragment.app.testing.launchFragmentInContainer
import com.plexus.marvel.usescase.home.HomeFragment
import com.plexus.marvel.usescase.home.HomeViewModel
import org.junit.Test
import org.junit.Before
import org.mockito.Mockito


class HomeViewModelTest {

    private val viewModel: HomeViewModel by lazy {
        Mockito.mock(HomeViewModel::class.java)
    }

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        scenario.onFragment { fragment ->
            fragment.apply {
                viewModel.goToCharacters = ::goToCharacters
            }
        }
    }

    @Test
    fun `user navigate to characters list`() {
        Mockito.verify(viewModel.goToCharacters).invoke()
    }

}