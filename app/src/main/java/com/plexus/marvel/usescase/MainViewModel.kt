package com.plexus.marvel.usescase

import android.app.Application
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel

class MainViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }
}