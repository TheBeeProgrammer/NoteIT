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
    RecyclerView.Adapter<NotesAdapter.Companion.NotesHolder>() {

    private var callBackAdapter: CallBackAdapter? = null

    fun setView(fragment: NotesListFragment) {
        callBackAdapter = fragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_notes, parent, false)
        return NotesHolder(view)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        val notes = lisNoteModels[position]
        tasksAdapter = TasksAdapter(notes.tasks)
        holder.bind(notes, tasksAdapter, context)
        holder.setCallBack(callBack, callBackAdapter)
    }

    override fun getItemCount() = lisNoteModels.size

    companion object {
        class NotesHolder(view: View) :
            RecyclerView.ViewHolder(view),
            View.OnClickListener {

            private lateinit var notesModel: NotesModel
            private var callBack: NotesListFragment.CallBacks? = null
            private var callBackAdapter: NotesAdapter.CallBackAdapter? = null

            private var titleTextView: TextView = view.findViewById(R.id.tv_title)
            private var dateTextView: TextView = view.findViewById(R.id.tv_date)
            private val rvTasks: RecyclerView = view.findViewById(R.id.rv_listTasks)

            fun bind(notesModel: NotesModel, tasksAdapter: TasksAdapter?, context: Context?) {
                this.notesModel = notesModel

                dateTextView.setFormatDate(notesModel.date)
                titleTextView.text = notesModel.name
                rvTasks.layoutManager = LinearLayoutManager(context)
                rvTasks.adapter = tasksAdapter
            }

            fun setCallBack(
                callBack: NotesListFragment.CallBacks?,
                callBackAdapter: CallBackAdapter?
            ) {
                this.callBack = callBack
                this.callBackAdapter = callBackAdapter
            }

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                callBackAdapter?.onNoteSelected(notesModel.id)
                callBack?.onNoteSelected(notesModel.id)
            }
        }
    }

    interface CallBackAdapter {
        fun onNoteSelected(id: UUID);
    }

}