package com.example.homeworknotes.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.R
import com.example.homeworknotes.model.HomeWorkNotes
import java.util.*

class NotesAdapter(private var lisHomeWorkNotes: List<HomeWorkNotes>) :
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
        val notes = lisHomeWorkNotes[position]
        holder.bind(notes)
    }

    override fun getItemViewType(position: Int) = position % 2

    override fun getItemCount() = lisHomeWorkNotes.size


    inner class NotesHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var homeWorkNotes: HomeWorkNotes
        private var nameTextView: TextView = view.findViewById(R.id.tv_name)
        private var dateTextView: TextView = view.findViewById(R.id.tv_notedate)


        fun bind(homeWorkNotes: HomeWorkNotes) {
            this.homeWorkNotes = homeWorkNotes
            nameTextView.text = homeWorkNotes.name
            dateTextView.text = homeWorkNotes.date.toString()
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener?.onItemClick(homeWorkNotes.id)
        }

    }

    interface Tap {
        fun onItemClick(id: UUID)
    }


}