package com.example.homeworknotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

//Define una clase como entidad
@Entity
data class NotesModel(
    @PrimaryKey val id: UUID = UUID.randomUUID(), // Especifica la clave primaria
    var name: String = "",
    var date: Date = Date(),
    var isComplete: Boolean = false
):Serializable