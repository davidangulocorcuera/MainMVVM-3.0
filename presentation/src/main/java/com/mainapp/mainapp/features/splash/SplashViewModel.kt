package com.mainapp.mainapp.features.splash

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mainapp.domain.model.Character
import com.mainapp.domain.usecases.network.GetAllCharactersUseCase
import com.mainapp.domain.usecases.local.SaveCharactersInDatabaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val saveCharactersInDatabaseUseCase: SaveCharactersInDatabaseUseCase,
    ) : ViewModel() {

    private val _splashState = MutableStateFlow<SplashState>(SplashState.Loading)
    val splashState: StateFlow<SplashState> = _splashState

    private val _splashNavigation = MutableSharedFlow<SplashNavigation>()
    val splashNavigation: Flow<SplashNavigation> = _splashNavigation

    private fun saveCharactersInDatabase(characters: List<Character>) {
        viewModelScope.launch {
            saveCharactersInDatabaseUseCase.saveAllCharacters(characters)
            _splashNavigation.emit(SplashNavigation.NavigateToHome)
        }
    }

    @SuppressLint("CheckResult")
    fun getAllCharacters() {
        viewModelScope.launch {
            _splashState.emit(SplashState.Loading)
        }
        getAllCharactersUseCase.getAllCharacters().apply {
            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewModelScope.launch {
                            it.data?.results?.let { characters ->
                                saveCharactersInDatabase(characters)
                            }
                        }
                    },
                    onError = {
                        viewModelScope.launch {
                            _splashState.emit(SplashState.ErrorLoadingCharactersState)
                        }
                    }
                )
        }
    }

    sealed class SplashNavigation {
        object NavigateToHome : SplashNavigation()
    }

    sealed class SplashState {
        object ErrorLoadingCharactersState : SplashState()
        object Loading : SplashState()
    }
}
