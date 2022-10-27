package com.plexus.marvel.base


import android.content.Context

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

interface GlobalAction {
    fun back(context: Context) {
        if (context is BaseActivity) {
            context.apply {
                navigator.goToLastFragment()
            }
        }
    }
}