package com.plexus.marvel.usescase

import android.os.Bundle
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseActivity
import com.plexus.marvel.databinding.ActivityMainBinding
import com.plexus.marvel.usescase.splash.SplashFragment

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