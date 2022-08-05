package com.plexus.marvel.usescase.characterdetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.domain.Character
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel
import com.plexus.marvel.base.mDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */


class CharacterDetailViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    var characterState = MutableLiveData<CharacterDetailState>()
    var loading = MutableLiveData<Boolean>()
    var showErrorButton = MutableLiveData<Boolean>(false)
    var character = MutableLiveData<Character>()

    fun getCharacterDetail(id: Int) {
        loading.value = true
        ServicesRepository().getCharacterDetail(id = id)?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        loading.value = false
                        it.data?.results?.first()?.apply {
                            characterState.value = CharacterDetailState.CharacterLoadedState(this)

                        } ?: run {
                            characterState.value = CharacterDetailState.ErrorLoadingCharacterState
                        }
                    },
                    onError = {
                        loading.value = false
                        characterState.value = CharacterDetailState.ErrorLoadingCharacterState
                    }
                )
            )
        }
    }
}