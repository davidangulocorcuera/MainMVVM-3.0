package com.mainapp.mainapp.features.characters

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mainapp.domain.model.Character
import com.mainapp.domain.usecases.network.GetAllCharactersUseCase
import com.mainapp.domain.usecases.local.GetCharactersFromDatabaseUseCase
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


@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharactersFromDatabaseUseCase: GetCharactersFromDatabaseUseCase,
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
        getAllCharactersUseCase.getAllCharacters(offset = offset).apply {
            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
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
            _characters.addAll(getCharactersFromDatabaseUseCase.getAllCharacters())
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