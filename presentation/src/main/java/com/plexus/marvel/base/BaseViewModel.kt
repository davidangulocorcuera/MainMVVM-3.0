package com.plexus.marvel.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

open class BaseViewModel(app: Application) : AndroidViewModel(app)

val mDisposable = CompositeDisposable()