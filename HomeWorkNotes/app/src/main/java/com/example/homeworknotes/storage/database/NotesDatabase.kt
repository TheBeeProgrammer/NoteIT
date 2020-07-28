package com.example.homeworknotes.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.storage.database.dao.NotesDao



@Database(entities = [NotesModel::class], version = 3,exportSchema = false)
@TypeConverters(NotesConverter::class)

abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

}


val migration_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE NotesModel ADD COLUMN tasks TEXT NOT NULL DEFAULT ''"
        )
    }
}
