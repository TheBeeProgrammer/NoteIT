package com.example.homeworknotes.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.homeworknotes.R
import java.util.*
private const val ARG_ID = "id"
class DeleteNoteDialog : DialogFragment() {

    private lateinit var delete: Button
    private lateinit var edit: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.delete_note_dialog, container, false)
        delete = view.findViewById(R.id.btn_delete)
        edit = view.findViewById(R.id.btn_edit)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        fun newInstance(id: UUID): DeleteNoteDialog {
            val args = Bundle().apply {
                putSerializable(ARG_ID, id)
            }

            return DeleteNoteDialog().apply {
                arguments = args
            }
        }

    }
}