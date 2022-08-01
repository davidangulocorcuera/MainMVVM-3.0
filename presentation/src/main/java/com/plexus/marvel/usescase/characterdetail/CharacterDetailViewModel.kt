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

    lateinit var onCharacterLoaded: (character: Character) -> Unit
    lateinit var onErrorLoadingCharacter: () -> Unit
    var loading = MutableLiveData<Boolean>()
    var showErrorButton = MutableLiveData<Boolean>(false)
    var character = MutableLiveData<Character>()

    fun getCharacterDetail(id: Int) {
        loading.value = true
        ServicesRepository(getApplication()).getCharacterDetail(id = id)?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        loading.value = false
                        it.data?.results?.first()?.apply {
                            onCharacterLoaded.invoke(this)
                        } ?: run {
                            onErrorLoadingCharacter.invoke()
                        }
                    },
                    onError = {
                        loading.value = false
                        onErrorLoadingCharacter.invoke()
                    }
                )
            )
        }
    }
}