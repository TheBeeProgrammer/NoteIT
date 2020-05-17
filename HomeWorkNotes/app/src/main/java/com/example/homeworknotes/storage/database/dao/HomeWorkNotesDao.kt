package com.example.homeworknotes.storage.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.homeworknotes.model.HomeWorkNotes
import java.util.*

@Dao
interface HomeWorkNotesDao {

    @Query("SELECT * FROM homeworknotes")
    fun getHomeWorkNotesList(): LiveData<List<HomeWorkNotes>>

    @Query("SELECT * FROM homeworknotes WHERE id=(:id)")
    fun getHomewWorkNote(id: UUID): LiveData<HomeWorkNotes?>

    @Update
    fun updateNote(note: HomeWorkNotes)

    @Insert
    fun addNote(note: HomeWorkNotes)
}