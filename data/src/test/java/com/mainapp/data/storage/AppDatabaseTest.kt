package com.mainapp.data.storage

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mainapp.data.storage.database.AppDatabase
import com.mainapp.data.storage.database.converters.CharacterDB
import com.mainapp.data.storage.database.converters.CharactersDao
import com.mainapp.domain.model.Image
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: CharactersDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.charactersDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun `insert and read character in database`() = kotlin.run {
        val current = CharacterDB(
            id = 1,
            name = "example",
            description = "example description",
            thumbnail = Image(
                path = "example path",
                extension = "jpg"
            )
        )
        dao.insertCharacter(current)
        val characters = dao.getAllCharacters()
        assertTrue(characters.contains(current))
    }


}