package com.plexus.marvel.home

import com.plexus.marvel.features.home.HomeViewModel
import org.mockito.Mockito


class HomeViewModelTest {

    private val viewModel: HomeViewModel by lazy {
        Mockito.mock(HomeViewModel::class.java)
    }
}