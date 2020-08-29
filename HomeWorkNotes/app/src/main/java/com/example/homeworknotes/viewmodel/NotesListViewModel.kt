package com.example.homeworknotes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.homeworknotes.storage.repository.NotesRepository


class NotesListViewModel : ViewModel() {

    private val notesRepository = NotesRepository.get()
    val notesLiveData = notesRepository.getNotes()

}