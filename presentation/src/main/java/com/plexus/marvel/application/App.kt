package com.plexus.marvel.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.plexus.data.storage.database.AppDatabase

class App : Application() {
    private lateinit var database: AppDatabase


    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getDatabase(context: Context): AppDatabase {
        if (!::database.isInitialized) {
            database = Room.databaseBuilder(context, AppDatabase::class.java, "marvel_database")
                .fallbackToDestructiveMigration()
                .build()
        }
        return database
    }

}