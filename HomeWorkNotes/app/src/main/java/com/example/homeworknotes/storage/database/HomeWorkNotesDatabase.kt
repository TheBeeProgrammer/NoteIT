package com.example.homeworknotes.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homeworknotes.model.HomeWorkNotes
import com.example.homeworknotes.storage.database.dao.HomeWorkNotesDao



@Database(entities = [HomeWorkNotes::class], version = 1,exportSchema = false)
@TypeConverters(HomeWorkNotesConverter::class)

abstract class HomeWorkNotesDatabase : RoomDatabase() {

    abstract fun homeWorkNotesDao(): HomeWorkNotesDao

}
