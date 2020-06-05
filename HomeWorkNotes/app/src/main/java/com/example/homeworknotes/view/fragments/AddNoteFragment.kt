package com.example.homeworknotes.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.homeworknotes.R
import java.text.SimpleDateFormat
import java.util.*

private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0

class AddNoteFragment : Fragment(), DatePickerFragment.Callbacks {

    private lateinit var tvSelectedDate: TextView
    private lateinit var buttonSave: Button
    private lateinit var tvAddNote: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = layoutInflater.inflate(R.layout.add_note_fragment, container, false)
        tvSelectedDate = view.findViewById(R.id.tv_select_date)
        buttonSave = view.findViewById(R.id.btn_save)
        tvAddNote = view.findViewById(R.id.tv_add_note)
        initUi()
        return view
    }

    private fun initUi() {
        tvSelectedDate.text = getFormatDate()
    }

    private fun getFormatDate(): String {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("dd MMMM yyyy")
        return formatter.format(calendar.time)
    }

    companion object {
        fun newInstance(): AddNoteFragment {
            return AddNoteFragment()
        }
    }

    override fun onDateSelected(date: Date) {
        tvSelectedDate.apply {
            text = date.toString()
        }
    }
}