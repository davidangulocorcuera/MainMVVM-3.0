package com.plexus.marvel.features.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel
import com.plexus.marvel.base.mDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class SplashViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    private val _splashState = MutableStateFlow<SplashState>(SplashState.Loading)
    val splashState: StateFlow<SplashState> = _splashState

    fun getAllCharacters() {
        viewModelScope.launch {
            _splashState.emit(SplashState.Loading)
        }
        ServicesRepository().getAllCharacters()?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        viewModelScope.launch {
                            it.data?.results?.let { characters ->
                                _splashState.emit(SplashState.CharactersLoadedState(characters))
                            }
                        }
                    },
                    onError = {
                        viewModelScope.launch {
                            _splashState.emit(SplashState.ErrorLoadingCharactersState)
                        }
                    }
                )
            )
        }
    }
}