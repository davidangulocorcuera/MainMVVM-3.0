package com.plexus.marvel.features.characters

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.data.storage.database.LocalRepository
import com.plexus.domain.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: LocalRepository,
    private val servicesRepository: ServicesRepository,
    ) : ViewModel() {

    private val _charactersState = MutableStateFlow<CharactersState>(CharactersState.Loading)
    val charactersState: StateFlow<CharactersState> = _charactersState

    private val _characters = mutableStateListOf<Character>()
    val characters: List<Character> = _characters

    var offset = MutableLiveData(0)

    @SuppressLint("CheckResult")
    fun getAllCharacters(offset: Int) {
        viewModelScope.launch {
            _charactersState.emit(CharactersState.Loading)
        }
        servicesRepository.getAllCharacters(offset = offset).apply {
            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it.data?.results?.let { characters ->
                            viewModelScope.launch {
                                _characters.addAll(characters)
                                _charactersState.emit(
                                    CharactersState.CharactersLoadedState(
                                        characters
                                    )
                                )
                            }
                        }
                    },
                    onError = {
                        viewModelScope.launch {
                            _charactersState.emit(CharactersState.ErrorLoadingCharactersState)
                        }
                    }
                )
        }
    }

    fun reloadCharacters() {
        _characters.clear()
        getAllCharacters(0)
    }

    fun getAllCharactersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.addAll(repository.getAllCharacters())
            withContext(Dispatchers.Main) {
                _charactersState.value = CharactersState.CharactersLoadedState(characters)
            }
        }
    }
    sealed class CharactersState {
        data class CharactersLoadedState(val characters: List<Character>) : CharactersState()
        object ErrorLoadingCharactersState : CharactersState()
        object Loading : CharactersState()
    }
    sealed class CharactersInstructions {
        data class CharacterClickedState(val id: Int) : CharactersInstructions()
    }
}