package com.example.homeworknotes.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.homeworknotes.model.HomeWorkNotes
import com.example.homeworknotes.storage.database.HomeWorkNotesDatabase
import java.util.*
import java.util.concurrent.Executors

/**
 *  Repository Pattern
 *  Singleton Pattern
 *  BackGround Thread Executor to insert and update data
 **/

private const val DATABAE_NAME = "notes-database"

class HomeWorkNotesRepository private constructor(context: Context) {

    private val homeWorkNotesDatabase = Room.databaseBuilder(
        context.applicationContext,
        HomeWorkNotesDatabase::class.java,
        DATABAE_NAME
    ).build()

    private val executor = Executors.newSingleThreadExecutor()

    private val homeWorkNotesDao = homeWorkNotesDatabase.homeWorkNotesDao()

    fun getNotes(): LiveData<List<HomeWorkNotes>> = homeWorkNotesDao.getHomeWorkNotesList()
    fun getNoteByID(id: UUID): LiveData<HomeWorkNotes?> = homeWorkNotesDao.getHomewWorkNote(id)

    fun addNote(note: HomeWorkNotes) {
        executor.execute {
            homeWorkNotesDao.addNote(note)
        }
    }

    fun updateNote(note: HomeWorkNotes){
        executor.execute{
            homeWorkNotesDao.updateNote(note)
        }
    }

    companion object {

        var INSTANCE: HomeWorkNotesRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = HomeWorkNotesRepository(context)
            }
        }

        fun get(): HomeWorkNotesRepository {
            return INSTANCE ?: throw IllegalStateException("NoteRepository must be initialized")
        }
    }
}
