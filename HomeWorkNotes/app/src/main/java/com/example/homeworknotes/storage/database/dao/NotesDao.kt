package com.example.homeworknotes.storage.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.homeworknotes.model.NotesModel
import java.util.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM NotesModel")
    fun getHomeWorkNotesList(): LiveData<List<NotesModel>>

    @Query("SELECT * FROM NotesModel WHERE id=(:id)")
    fun getHomewWorkNote(id: UUID): LiveData<NotesModel?>

    @Update
    fun updateNote(note: NotesModel)

    @Insert
    fun addNote(note: NotesModel)
}