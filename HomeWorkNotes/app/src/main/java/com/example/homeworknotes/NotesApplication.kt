package com.example.homeworknotes

import android.app.Application
import com.example.homeworknotes.storage.repository.NotesRepository

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NotesRepository.initialize(this)
    }
}