package com.plexus.marvel.features.characterdetail

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewModelScope
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.domain.Character
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


class CharacterDetailViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    private val _characterState =
        MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val characterState: StateFlow<CharacterDetailState> = _characterState



    fun getCharacterDetail(id: Int) {
        ServicesRepository().getCharacterDetail(id = id)?.apply {
            viewModelScope.launch {
                _characterState.emit(CharacterDetailState.Loading)
            }
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it.data?.results?.first()?.let { character ->
                            viewModelScope.launch {
                                _characterState.emit(
                                    CharacterDetailState.CharacterLoadedState(
                                        character
                                    )
                                )
                            }
                        }
                    },
                    onError = {
                        viewModelScope.launch {
                            _characterState.emit(CharacterDetailState.ErrorLoadingCharacterState)
                        }
                    }
                )
            )
        }
    }
}