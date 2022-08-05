package com.plexus.marvel.usescase.characters

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.domain.Character
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel
import com.plexus.marvel.base.mDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class CharactersViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }
    var loading = MutableLiveData<Boolean>()
    var charactersState = MutableLiveData<CharactersState>()
    private val _viewInstructions = MutableSharedFlow<CharactersInstructions>()
    val viewInstructions: Flow<CharactersInstructions> = _viewInstructions
    var showErrorButton = MutableLiveData(false)

    fun getAllCharacters(offset: Int) {
        showErrorButton.value = false
        loading.value = true
        ServicesRepository().getAllCharacters(offset = offset)?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        loading.value = false
                        it.data?.results?.apply {
                            charactersState.value = CharactersState.CharactersLoadedState(this)
                        } ?: run {
                            charactersState.value = CharactersState.ErrorLoadingCharactersState
                        }
                    },
                    onError = {
                        loading.value = false
                        charactersState.value = CharactersState.ErrorLoadingCharactersState
                    }
                )
            )
        }
    }

    fun goToCharacterDetail(id: Int){
        viewModelScope.launch {
            _viewInstructions.emit(CharactersInstructions.CharacterClickedState(id))
        }
    }

}