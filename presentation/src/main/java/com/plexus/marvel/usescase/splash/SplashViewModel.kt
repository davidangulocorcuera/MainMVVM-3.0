package com.plexus.marvel.usescase.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel
import com.plexus.marvel.base.mDisposable
import com.plexus.marvel.usescase.home.SplashState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class SplashViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    var loading = MutableLiveData<Boolean>()
    var splashState = MutableLiveData<SplashState>()
    var showErrorButton = MutableLiveData<Boolean>(false)

    fun getAllCharacters() {
        showErrorButton.value = false
        loading.value = true
        ServicesRepository().getAllCharacters()?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        loading.value = false
                        it.data?.results?.apply {
                            splashState.value = SplashState.CharactersLoadedState(this)
                        } ?: run {
                            splashState.value = SplashState.ErrorLoadingCharactersState
                        }
                    },
                    onError = {
                        loading.value = false
                        splashState.value = SplashState.ErrorLoadingCharactersState
                    }
                )
            )
        }
    }
}