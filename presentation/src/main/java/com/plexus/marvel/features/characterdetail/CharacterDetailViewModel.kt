package com.plexus.marvel.features.characterdetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.data.storage.database.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: LocalRepository,
    app: Application
) : ViewModel() {
    private val _characterState =
        MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val characterState: StateFlow<CharacterDetailState> = _characterState


    fun getCharacterDetail(id: Int) {
        ServicesRepository().getCharacterDetail(id = id)?.apply {
            viewModelScope.launch {
                _characterState.emit(CharacterDetailState.Loading)
            }
            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
        }
    }
}
