package com.plexus.marvel.usescase.characters

import android.app.Application
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel

class CharactersViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }
}