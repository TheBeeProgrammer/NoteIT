package com.example.homeworknotes.storage.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

/**
Room is able to store primitive types, so Room use a TypeConverter to storage this Data
 */
class NotesConverter {

    @TypeConverter
    fun fromDate(date: Date?) = date?.time

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?) = UUID.fromString(uuid)

    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid?.toString()

    @TypeConverter
    fun toJson(tasks: MutableList<String>?) = Gson().toJson(tasks)

    @TypeConverter
    fun fromJson(tasks: String?) =
        Gson().fromJson(tasks, Array<String>::class.java).toMutableList()

}