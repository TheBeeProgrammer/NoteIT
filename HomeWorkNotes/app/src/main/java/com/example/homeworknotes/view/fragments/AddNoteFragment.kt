package com.example.homeworknotes.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.R
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.utils.setCurrentDate
import com.example.homeworknotes.view.adapter.TasksAdapter
import com.example.homeworknotes.view.dialogs.AddTasksDialog
import com.example.homeworknotes.viewmodel.NotesDetailsViewModel

private const val REQUEST_TASK = 0
private const val TAG = "AddNoteFragment"

class AddNoteFragment : Fragment(), AddTasksDialog.ListenerTask {


    private lateinit var buttonSave: Button
    private lateinit var tvAddNote: TextView
    private lateinit var tvSelectedDate: TextView
    private lateinit var rvTasks: RecyclerView
    private lateinit var edtTitle: EditText

    private val taskList: MutableList<String> = mutableListOf()
    private var tasksAdapter: TasksAdapter = TasksAdapter(emptyList())
    private var notesModel: NotesModel = NotesModel()

    private val notesDetailsViewModel: NotesDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(NotesDetailsViewModel::class.java)
    }


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
        rvTasks = view.findViewById(R.id.rv_tasks)
        edtTitle = view.findViewById(R.id.edt_title)
        initUi()
        configUi()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
    }

    private fun initUi() {
        tvSelectedDate.setCurrentDate()
    }


    private fun updateRv(task: String) {
        taskList.add(task)
        tasksAdapter = TasksAdapter(taskList)
        rvTasks.adapter = tasksAdapter
        tasksAdapter.notifyDataSetChanged()
    }

    private fun configUi() {
        tvAddNote.setOnClickListener {
            AddTasksDialog.newInstance().apply {
                setTargetFragment(this@AddNoteFragment, REQUEST_TASK)
                show(this@AddNoteFragment.requireFragmentManager(), "")
            }
        }

        buttonSave.setOnClickListener {
            notesModel.tasks = taskList
            notesModel.name = edtTitle.text.toString()
            notesDetailsViewModel.saveNote(notesModel)
        }
    }

    private fun setUpRecyclerView() {
        rvTasks.layoutManager = LinearLayoutManager(context)
        rvTasks.adapter = tasksAdapter
    }

    companion object {
        fun newInstance(): AddNoteFragment {
            return AddNoteFragment()
        }
    }

    override fun getTask(task: String) {
            if (task.isNotEmpty()) updateRv(task)
    }
}