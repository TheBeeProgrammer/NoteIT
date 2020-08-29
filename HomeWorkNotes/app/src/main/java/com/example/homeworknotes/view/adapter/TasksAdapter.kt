package com.example.homeworknotes.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.R
import com.example.homeworknotes.model.NotesModel


private const val TAG = "TasksAdapter"

class TasksAdapter(private val tasksList: List<String>) :
    RecyclerView.Adapter<TasksAdapter.Companion.TasksHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_add_tasks, parent, false)
        return TasksHolder(view)
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: TasksHolder, position: Int) {
        Log.d(TAG, tasksList[position])
        holder.bind(tasksList[position])
    }

    companion object {
        class TasksHolder(view: View) : RecyclerView.ViewHolder(view) {
            private var tvTask: TextView = view.findViewById(R.id.tv_task)

            fun bind(tasks: String) {
                tvTask.text = tasks
            }
        }
    }

}