package com.plexus.marvel.application

import android.app.Application

class App : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}