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
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.utils.setFormatDate
import com.example.homeworknotes.view.dialogs.DatePickerFragment
import com.example.homeworknotes.viewmodel.NotesViewModel
import java.util.*

private const val ARG_HOMEWORK_ID = "note_id"
private const val TAG = "NotesFragment"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0


class NotesFragment : Fragment(), DatePickerFragment.Callbacks {

    private lateinit var note: NotesModel
    private lateinit var edtName: EditText
    private lateinit var btnDate: Button
    private lateinit var checkBoxComplete: CheckBox

    private val notesViewModel: NotesViewModel by lazy {
        ViewModelProviders.of(this).get(NotesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note = NotesModel()
        val noteId: UUID = arguments?.getSerializable(ARG_HOMEWORK_ID) as UUID
        notesViewModel.loadNote(noteId)
        Log.d(TAG, "args bundle note ID: $noteId")
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
        configUI()
        return view
    }

    override fun onStart() {
        super.onStart()
        watcherEditText()
        initUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel.noteLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { notes ->
                notes?.let {
                    this.note = notes
                    initUI()
                }
            })
    }

    private fun configUI() {
        btnDate.setOnClickListener {
            DatePickerFragment.newInstance(note.date).apply {
                setTargetFragment(this@NotesFragment, REQUEST_DATE)
                show(this@NotesFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }
    }

    private fun initUI() {
        edtName.setText(note.name)
        btnDate.setFormatDate(note.date)
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

    fun newInstance(homeWorkId: UUID): NotesFragment {
        val args = Bundle().apply {
            putSerializable(ARG_HOMEWORK_ID, homeWorkId)
        }
        return NotesFragment().apply {
            arguments = args
        }
    }

    override fun onDateSelected(date: Date) {
        note.date = date
        initUI()
    }


}

