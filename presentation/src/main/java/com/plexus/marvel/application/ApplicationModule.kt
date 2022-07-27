package com.plexus.marvel.application

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(var app: App) {
    @Provides
    @Singleton
    fun provideApp(): App = app
}