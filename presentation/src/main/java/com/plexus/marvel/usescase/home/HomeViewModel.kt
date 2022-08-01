package com.plexus.marvel.usescase.home

import android.app.Application
import com.plexus.domain.Character
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class HomeViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }
    lateinit var goToCharacters: () -> Unit

}