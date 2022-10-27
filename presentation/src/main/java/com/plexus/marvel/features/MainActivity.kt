package com.plexus.marvel.features

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.plexus.marvel.base.BaseActivity
import com.plexus.marvel.components.CustomNavGraph
import com.plexus.marvel.features.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomNavGraph()
        }
    }
}