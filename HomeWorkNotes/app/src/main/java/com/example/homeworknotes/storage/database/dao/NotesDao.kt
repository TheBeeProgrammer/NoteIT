package com.example.homeworknotes.storage.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.homeworknotes.model.NotesModel
import java.util.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM NotesModel")
    fun getNotesList(): LiveData<List<NotesModel>>

    @Query("SELECT * FROM NotesModel WHERE id=(:id)")
    fun getNote(id: UUID): LiveData<NotesModel?>

    @Update
    fun updateNote(note: NotesModel)

    @Insert
    fun addNote(note: NotesModel)

    @Delete
    fun deleteNote(notesModel: NotesModel)

}