package com.example.homeworknotes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.homeworknotes.model.HomeWorkNotes
import com.example.homeworknotes.repository.HomeWorkNotesRepository

class HomeWorkNotesListViewModel : ViewModel() {
    val homeWorkNotesList = mutableListOf<HomeWorkNotes>()

    init {
       for (i in 1..100) {
            val homeWorkNotes = HomeWorkNotes()
            homeWorkNotes.name = "Note #$i"
            homeWorkNotes.isComplete = i % 2 == 0
            homeWorkNotesList += homeWorkNotes
        }

    }

    /*private val crimeRepository = HomeWorkNotesRepository.get()
    val notesListLiveData = crimeRepository.getNotes()*/
}