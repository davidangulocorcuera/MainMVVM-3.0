package com.plexus.marvel.features.splash

import android.annotation.SuppressLint
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
import com.plexus.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {
    private val _splashState = MutableStateFlow<SplashState>(SplashState.Loading)
    val splashState: StateFlow<SplashState> = _splashState

    private val _splashNavigation = MutableSharedFlow<SplashNavigation>()
    val splashNavigation: Flow<SplashNavigation> = _splashNavigation

    private fun saveCharactersInDatabase(characters: List<Character>) {
        viewModelScope.launch {
            repository.saveAllCharacters(characters)
            _splashNavigation.emit(SplashNavigation.NavigateToHome)
        }
    }

    @SuppressLint("CheckResult")
    fun getAllCharacters() {
        viewModelScope.launch {
            _splashState.emit(SplashState.Loading)
        }
        ServicesRepository().getAllCharacters()?.apply {
            subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
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
