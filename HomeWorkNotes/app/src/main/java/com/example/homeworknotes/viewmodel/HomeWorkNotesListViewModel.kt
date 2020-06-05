package com.example.homeworknotes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.repository.NotesRepository


class HomeWorkNotesListViewModel : ViewModel() {

    val homeWorkNotesList = mutableListOf<NotesModel>()
    private val notesRepository = NotesRepository.get()
    val notesLiveData = notesRepository.getNotes()

   /* init {
        for (i in 1..100) {
            val notesModel = NotesModel()
            notesModel.name = "Note #$i"
            notesModel.isComplete = i % 2 == 0
            homeWorkNotesList += notesModel
        }

    }*/

    fun addNote(note: NotesModel) {
        val notesModel = NotesModel()
        notesModel.name = "Compras"
        notesModel.isComplete = false
        notesRepository.addNote(note)
    }

}