package com.mainapp.mainapp.features.characterdetail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mainapp.domain.model.Character
import com.mainapp.domain.usecases.network.GetCharacterDetailUseCase
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
    private val useCase: GetCharacterDetailUseCase,
    ) : ViewModel() {
    private val _characterState =
        MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val characterState: StateFlow<CharacterDetailState> = _characterState


    @SuppressLint("CheckResult")
    fun getCharacterDetail(id: Int) {
        useCase.getCharacterDetail(id = id).apply {
            viewModelScope.launch {
                _characterState.emit(CharacterDetailState.Loading)
            }
            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
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

    sealed class CharacterDetailState {
        data class CharacterLoadedState(val character: Character) : CharacterDetailState()
        object ErrorLoadingCharacterState : CharacterDetailState()
        object Loading : CharacterDetailState()
    }
}
