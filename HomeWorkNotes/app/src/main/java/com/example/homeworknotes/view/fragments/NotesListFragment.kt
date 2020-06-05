package com.example.homeworknotes.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.R
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.view.adapter.NotesAdapter
import com.example.homeworknotes.viewmodel.HomeWorkNotesListViewModel
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class NotesListFragment : Fragment(), NotesAdapter.Tap {

    private lateinit var rvNotes: RecyclerView
    private var notesAdapter: NotesAdapter? = NotesAdapter(emptyList())
    private var callBacks: CallBacks? = null
    private lateinit var fabAdd: FloatingActionButton

    private val homeWorkNotesListViewModel: HomeWorkNotesListViewModel by lazy {
        ViewModelProviders.of(this).get(HomeWorkNotesListViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notesAdapter?.setListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        rvNotes = view.findViewById(R.id.rv_listNotes)
        fabAdd = view.findViewById(R.id.fab_add_notes)
        rvNotes.layoutManager = LinearLayoutManager(context)
        rvNotes.adapter = notesAdapter
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_note, menu)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBacks = context as CallBacks?
    }

    override fun onDetach() {
        super.onDetach()
        callBacks = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configUI()
        renderUI()
    }

    private fun updateUI(notes: List<NotesModel>) {
        notesAdapter =
            NotesAdapter(notes)
        rvNotes.adapter = notesAdapter

    }

    private fun renderUI() {
        homeWorkNotesListViewModel.notesLiveData.observe(
            viewLifecycleOwner,
            Observer { notes ->
                notes.let {
                    updateUI(notes)
                }
            })
    }

    private fun configUI() {
        fabAdd.setOnClickListener {
            callBacks?.navigateToAddFragment()
        }
    }

    companion object {
        fun newInstance(): NotesListFragment {
            return NotesListFragment()
        }
    }

    interface CallBacks {
        fun onHomeWorkSelected(id: UUID)
        fun navigateToAddFragment()
    }

    override fun onItemClick(id: UUID) {
        callBacks?.onHomeWorkSelected(id)
    }


}