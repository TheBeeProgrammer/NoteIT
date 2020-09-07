package com.example.homeworknotes.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.R
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.utils.setFormatDate
import com.example.homeworknotes.view.fragments.NotesListFragment
import java.util.*

private const val TAG = "NotesAdapter"

class NotesAdapter(
    private var lisNoteModels: List<NotesModel>,
    private var tasksAdapter: TasksAdapter?,
    private var context: Context?,
    private var callBack: NotesListFragment.CallBacks?
) :
    RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private var listener: OnClickAdapter? = null

    fun setListener(fragment: Fragment) {
        listener = fragment as OnClickAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_notes, parent, false)
        return NotesHolder(view)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        val notes = lisNoteModels[position]
        tasksAdapter = TasksAdapter(notes.tasks)
        holder.bind(notes)
    }

    override fun getItemCount() = lisNoteModels.size


    inner class NotesHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var notesModel: NotesModel
        private var titleTextView: TextView = view.findViewById(R.id.tv_title)
        private var dateTextView: TextView = view.findViewById(R.id.tv_date)
        private val rvTasks: RecyclerView = view.findViewById(R.id.rv_listTasks)

        fun bind(notesModel: NotesModel) {
            this.notesModel = notesModel

            dateTextView.setFormatDate(notesModel.date)
            titleTextView.text = notesModel.name
            rvTasks.layoutManager = LinearLayoutManager(context)
            rvTasks.adapter = tasksAdapter
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            callBack?.onHomeWorkSelected(notesModel.id)
        }

    }

    interface OnClickAdapter {
        fun onItemClick(id: UUID)
    }


}