package com.example.homeworknotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.homeworknotes.model.HomeWorkNotes
import com.example.homeworknotes.repository.HomeWorkNotesRepository
import java.util.*

class HomeWorkNotesDetailsViewModel : ViewModel() {

    private val homeWorkNotesRepository = HomeWorkNotesRepository.get()
    private val noteIdLiveData = MutableLiveData<UUID>()


    var noteLiveData: LiveData<HomeWorkNotes?> =
        Transformations.switchMap(noteIdLiveData) { noteId ->
            homeWorkNotesRepository.getNoteByID(noteId)
        }

    fun loadNote(crimeId: UUID) {
        noteIdLiveData.value = crimeId
    }

    fun saveNote(note: HomeWorkNotes){
        homeWorkNotesRepository.addNote(note)
    }

    fun updateNote(note: HomeWorkNotes){
        homeWorkNotesRepository.updateNote(note)
    }

}