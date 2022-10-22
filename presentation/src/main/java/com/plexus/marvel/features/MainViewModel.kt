package com.plexus.marvel.features

import android.app.Application
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel


/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class MainViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }
}