package com.plexus.marvel.features.characters

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.data.storage.database.LocalRepository
import com.plexus.domain.Character
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel
import com.plexus.marvel.base.mDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class CharactersViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }


    private val _charactersState = MutableStateFlow<CharactersState>(CharactersState.Loading)
    val charactersState: StateFlow<CharactersState> = _charactersState

    private val _characters = mutableStateListOf<Character>()
    val characters: List<Character> = _characters

    fun getAllCharacters(offset: Int) {
        viewModelScope.launch {
            _charactersState.emit(CharactersState.Loading)
        }
        ServicesRepository().getAllCharacters(offset = offset)?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
            )
        }
    }

    fun reloadCharacters(){
        _characters.clear()
        getAllCharacters(0)
    }

    fun getAllCharactersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.addAll(LocalRepository().getAllCharacters(App().getDatabase(getApplication())))
            withContext(Dispatchers.Main) {
                _charactersState.value = CharactersState.CharactersLoadedState(characters)
            }
        }
    }
}