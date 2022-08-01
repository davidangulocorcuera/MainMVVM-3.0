package com.plexus.marvel.application

import com.plexus.marvel.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@Singleton
    @Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun app(): App
    fun inject(baseViewModel: BaseViewModel)
}
