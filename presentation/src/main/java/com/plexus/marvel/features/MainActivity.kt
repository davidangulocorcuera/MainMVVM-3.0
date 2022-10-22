package com.plexus.marvel.features

import android.os.Bundle
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseActivity
import com.plexus.marvel.databinding.ActivityMainBinding
import com.plexus.marvel.features.splash.SplashFragment

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class.java
) {

    override fun initViewModel(viewModel: MainViewModel) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.navigate(SplashFragment(), addBackStack = false)
    }
}