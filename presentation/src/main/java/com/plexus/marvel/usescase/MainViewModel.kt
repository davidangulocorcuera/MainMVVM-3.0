package com.plexus.marvel.usescase

import android.app.Application
import android.util.Log
import com.plexus.data.cloud.repository.ServicesRepository
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseViewModel
import com.plexus.marvel.base.mDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    fun getAllCharacters() {
        ServicesRepository(getApplication()).getAllCharacters()?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        getCharacterDetail(it.data?.results!![0].id)
                    },
                    onError = {
                        Log.v("personajes","error")
                    }
                )
            )
        }
    }
    fun getCharacterDetail(id: Int) {
        ServicesRepository(getApplication()).getCharacterDetail(id = id)?.apply {
            mDisposable.add(subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {


                    },
                    onError = {
                        Log.v("personajes","error")
                    }
                )
            )
        }
    }
}