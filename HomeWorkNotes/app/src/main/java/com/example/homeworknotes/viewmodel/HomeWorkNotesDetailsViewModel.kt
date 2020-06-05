package com.example.homeworknotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.repository.NotesRepository
import java.util.*

class HomeWorkNotesDetailsViewModel : ViewModel() {

    private val homeWorkNotesRepository = NotesRepository.get()
    private val noteIdLiveData = MutableLiveData<UUID>()


    var noteLiveData: LiveData<NotesModel?> =
        Transformations.switchMap(noteIdLiveData) { noteId ->
            homeWorkNotesRepository.getNoteByID(noteId)
        }

    fun loadNote(crimeId: UUID) {
        noteIdLiveData.value = crimeId
    }

    fun saveNote(note: NotesModel){
        homeWorkNotesRepository.addNote(note)
    }

    fun updateNote(note: NotesModel){
        homeWorkNotesRepository.updateNote(note)
    }

}