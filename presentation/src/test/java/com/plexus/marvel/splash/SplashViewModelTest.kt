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
}