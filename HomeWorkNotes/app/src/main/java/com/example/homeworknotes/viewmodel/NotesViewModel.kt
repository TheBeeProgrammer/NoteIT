package com.example.homeworknotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.storage.repository.NotesRepository
import java.util.*

class NotesViewModel : ViewModel() {

    private val notesRepository = NotesRepository.get()
    private val noteIdLiveData = MutableLiveData<UUID>()

    var noteLiveData: LiveData<NotesModel?> =
        Transformations.switchMap(noteIdLiveData) { noteId ->
            notesRepository.getNoteByID(noteId)
        }

    fun loadNote(crimeId: UUID) {
        noteIdLiveData.value = crimeId
    }

    fun saveNote(note: NotesModel) {
        notesRepository.addNote(note)
    }

    fun updateNote(note: NotesModel) {
        notesRepository.updateNote(note)
    }

     fun deleteNote(note: NotesModel) {
        notesRepository.deleteNoteModel(note)
    }

}