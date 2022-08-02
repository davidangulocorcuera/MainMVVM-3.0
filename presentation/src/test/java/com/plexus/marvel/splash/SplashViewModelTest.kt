package com.plexus.marvel.splash


import androidx.fragment.app.testing.launchFragmentInContainer
import com.plexus.marvel.usescase.splash.SplashFragment
import com.plexus.marvel.usescase.splash.SplashViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class SplashViewModelTest {

    private val viewModel: SplashViewModel by lazy {
        Mockito.mock(SplashViewModel::class.java)
    }

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<SplashFragment>()
        scenario.onFragment { fragment ->
            fragment.apply {
                viewModel.onCharactersLoaded = ::onCharactersLoaded
                viewModel.onErrorLoadingCharacters = ::onErrorLoadingCharacters
            }
        }
    }

    @Test
    fun `characters loaded should invoke onCharactersLoaded`() {
        verify(viewModel.onCharactersLoaded).invoke(ArrayList())
    }

    @Test
    fun `characters error should invoke onErrorLoadingCharacters`() {
        verify(viewModel.onErrorLoadingCharacters).invoke()
    }
}