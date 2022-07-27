package com.plexus.marvel.application

import com.plexus.marvel.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
    @Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun app(): App
    fun inject(baseViewModel: BaseViewModel)
}
