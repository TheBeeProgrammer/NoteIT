package com.example.homeworknotes

import android.app.Application
import com.example.homeworknotes.repository.HomeWorkNotesRepository

class HomeWorkNotesApllication : Application() {

    override fun onCreate() {
        super.onCreate()
        HomeWorkNotesRepository.initialize(this)
    }
}