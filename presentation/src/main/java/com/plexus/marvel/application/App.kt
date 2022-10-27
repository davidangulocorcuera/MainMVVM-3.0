package com.plexus.marvel.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.plexus.data.storage.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

@HiltAndroidApp
class App : Application()