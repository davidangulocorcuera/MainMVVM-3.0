package com.plexus.marvel.usescase.characterdetail

import android.app.Application
import android.util.Log
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.domain.Character
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel
import com.plexus.marvel.base.mDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    lateinit var onCharacterLoaded: (character: Character) -> Unit
    lateinit var onErrorLoadingCharacter: () -> Unit

    fun getCharacterDetail(id: Int) {
        ServicesRepository(getApplication()).getCharacterDetail(id = id)?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it.data?.apply {
                            onCharacterLoaded.invoke(this)
                        } ?: run {
                            onErrorLoadingCharacter.invoke()
                        }
                    },
                    onError = {
                        onErrorLoadingCharacter.invoke()
                    }
                )
            )
        }
    }
}