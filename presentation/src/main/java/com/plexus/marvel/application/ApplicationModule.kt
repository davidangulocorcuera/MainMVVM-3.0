package com.plexus.marvel.application

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@Module
class ApplicationModule(var app: App) {
    @Provides
    @Singleton
    fun provideApp(): App = app
}