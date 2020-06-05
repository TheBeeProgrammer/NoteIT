package com.example.homeworknotes.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.R
import com.example.homeworknotes.model.NotesModel
import java.util.*

class NotesAdapter(private var lisNoteModels: List<NotesModel>) :
    RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    var listener: Tap? = null

    fun setListener(fragment: Fragment) {
        this.listener = fragment as Tap
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_homeworknotes, parent, false)
        return NotesHolder(view)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        val notes = lisNoteModels[position]
        holder.bind(notes)
    }

    override fun getItemViewType(position: Int) = position % 2

    override fun getItemCount() = lisNoteModels.size


    inner class NotesHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var notesModel: NotesModel
        private var nameTextView: TextView = view.findViewById(R.id.tv_name)
        private var dateTextView: TextView = view.findViewById(R.id.tv_notedate)


        fun bind(notesModel: NotesModel) {
            this.notesModel = notesModel
            nameTextView.text = notesModel.name
            dateTextView.text = notesModel.date.toString()
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener?.onItemClick(notesModel.id)
        }

    }

    interface Tap {
        fun onItemClick(id: UUID)
    }


}