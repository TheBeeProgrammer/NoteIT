package com.example.homeworknotes.storage.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.storage.database.NotesDatabase
import com.example.homeworknotes.storage.database.migration_2_3
import java.util.*
import java.util.concurrent.Executors

/**
 *  Repository Pattern
 *  Singleton Pattern
 *  BackGround Thread Executor to insert and update data
 **/

private const val DATABAE_NAME = "notes-database"

class NotesRepository private constructor(context: Context) {

    private val notesDataBase = Room.databaseBuilder(
        context.applicationContext,
        NotesDatabase::class.java,
        DATABAE_NAME
    ).addMigrations(migration_2_3).build()

    private val executor = Executors.newSingleThreadExecutor()

    private val notesDao = notesDataBase.notesDao()

    fun getNotes(): LiveData<List<NotesModel>> = notesDao.getHomeWorkNotesList()
    fun getNoteByID(id: UUID): LiveData<NotesModel?> = notesDao.getHomewWorkNote(id)

    fun addNote(note: NotesModel) {
        executor.execute {
            notesDao.addNote(note)
        }
    }

    fun updateNote(note: NotesModel) {
        executor.execute {
            notesDao.updateNote(note)
        }
    }

    companion object {

        var INSTANCE: NotesRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NotesRepository(context)
            }
        }

        fun get(): NotesRepository {
            return INSTANCE ?: throw IllegalStateException("NoteRepository must be initialized")
        }
    }
}
