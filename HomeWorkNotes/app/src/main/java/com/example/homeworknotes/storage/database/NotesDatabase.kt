package com.example.homeworknotes.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.storage.database.dao.NotesDao



@Database(entities = [NotesModel::class], version = 2,exportSchema = false)
@TypeConverters(NotesConverter::class)

abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

}
