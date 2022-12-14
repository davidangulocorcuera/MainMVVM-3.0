package com.mainapp.mainapp.features

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.mainapp.mainapp.navigation.CustomNavGraph
import dagger.hilt.android.AndroidEntryPoint

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomNavGraph()
        }
    }
}