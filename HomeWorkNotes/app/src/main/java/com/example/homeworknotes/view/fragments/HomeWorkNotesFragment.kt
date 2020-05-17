package com.example.homeworknotes.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.homeworknotes.R
import com.example.homeworknotes.model.HomeWorkNotes
import com.example.homeworknotes.viewmodel.HomeWorkNotesDetailsViewModel
import java.util.*

private const val ARG_HOMEWORK_ID = "homework_id"
private const val TAG = "HomeWorkNotesFragment"

class HomeWorkNotesFragment : Fragment() {

    private lateinit var note: HomeWorkNotes
    private lateinit var edtName: EditText
    private lateinit var btnDate: Button
    private lateinit var checkBoxComplete: CheckBox

    private val notesDetailsViewModel: HomeWorkNotesDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(HomeWorkNotesDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note = HomeWorkNotes()
        val noteId: UUID = arguments?.getSerializable(ARG_HOMEWORK_ID) as UUID
        notesDetailsViewModel.loadNote(noteId)
        Log.d(TAG, "args bundle crime ID: $noteId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_homeworknotes, container, false)
        edtName = view.findViewById(R.id.edt_namehomework)
        btnDate = view.findViewById(R.id.btn_homeworkdata)
        checkBoxComplete = view.findViewById(R.id.checkbox_complete)
        return view
    }

    override fun onStart() {
        super.onStart()
        watcherEditText()
        initUI()
    }

    override fun onStop() {
        super.onStop()
        notesDetailsViewModel.saveNote(note)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesDetailsViewModel.noteLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { notes ->
                notes?.let {
                    this.note = notes
                    initUI()
                }
            })
    }

    private fun initUI() {
        edtName.setText(note.name)
        btnDate.text = note.date.toString()
        checkBoxComplete.apply {
            isChecked = note.isComplete
            jumpDrawablesToCurrentState()
        }
    }

    private fun watcherEditText() {
        val nameWatcher = object : TextWatcher {
            override fun afterTextChanged(sequence: Editable?) {
                // nothing
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                note.name = sequence.toString()
            }

            override fun beforeTextChanged(
                ssequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //nothing
            }

        }
        edtName.addTextChangedListener(nameWatcher)
    }

    fun newInstance(homeWorkId: UUID): HomeWorkNotesFragment {
        val args = Bundle().apply {
            putSerializable(ARG_HOMEWORK_ID, homeWorkId)
        }
        return HomeWorkNotesFragment().apply {
            arguments = args
        }
    }
}

