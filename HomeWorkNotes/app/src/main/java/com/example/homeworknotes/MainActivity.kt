package com.example.homeworknotes

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworknotes.view.fragments.AddNoteFragment
import com.example.homeworknotes.view.fragments.NotesFragment
import com.example.homeworknotes.view.fragments.NotesListFragment
import java.util.*

const val TAG = "MAINACTIVITY"

class MainActivity : AppCompatActivity(), NotesListFragment.CallBacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        addFragment(currentFragment)
    }

    private fun addFragment(currentFragment: Any?) {
        if (currentFragment == null) {
            val fragment = NotesListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onHomeWorkSelected(id: UUID) {
        Log.d(TAG, " $id")
        val fragment = NotesFragment().newInstance(id)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null).commit()
    }

    override fun navigateToAddFragment() {
        val fragment = AddNoteFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null).commit()
    }
}
