package com.example.homeworknotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.homeworknotes.model.HomeWorkNotes
import com.example.homeworknotes.view.fragments.HomeWorkNotesFragment
import com.example.homeworknotes.view.fragments.HomeWorkNotesListFragment
import java.util.*

class MainActivity : AppCompatActivity(), HomeWorkNotesListFragment.CallBacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        addFragment(currentFragment)
    }

    private fun addFragment(currentFragment: Any?) {
        if (currentFragment == null) {
            val fragment = HomeWorkNotesListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onHomeWorkSelected(id: UUID) {
        Log.d("MAIN.CallBAck", " $id")
        val fragment = HomeWorkNotesFragment().newInstance(id)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null).commit()
    }
}
