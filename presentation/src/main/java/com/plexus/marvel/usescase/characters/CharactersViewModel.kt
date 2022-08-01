package com.plexus.marvel.usescase.characters

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

class CharactersViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    lateinit var goToCharacterDetail: (id: Int) -> Unit
    lateinit var onCharactersLoaded: (characters: ArrayList<Character>) -> Unit
    lateinit var onErrorLoadingCharacters: () -> Unit
    var loading = MutableLiveData<Boolean>()
    var showErrorButton = MutableLiveData<Boolean>(false)

    fun getAllCharacters(offset: Int) {
        showErrorButton.value = false
        loading.value = true
        ServicesRepository(getApplication()).getAllCharacters(offset = offset)?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        loading.value = false
                        it.data?.results?.apply {
                            onCharactersLoaded.invoke(this)
                        } ?: run {
                            onErrorLoadingCharacters.invoke()
                        }
                    },
                    onError = {
                        loading.value = false
                        onErrorLoadingCharacters.invoke()
                    }
                )
            )
        }
    }

}