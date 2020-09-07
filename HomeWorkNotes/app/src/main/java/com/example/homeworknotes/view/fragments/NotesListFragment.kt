package com.example.homeworknotes.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.R
import com.example.homeworknotes.model.NotesModel
import com.example.homeworknotes.view.adapter.NotesAdapter
import com.example.homeworknotes.view.adapter.TasksAdapter
import com.example.homeworknotes.viewmodel.NotesListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class NotesListFragment : Fragment(), NotesAdapter.OnClickAdapter {

    private lateinit var rvNotes: RecyclerView
    private var tasksAdapter: TasksAdapter = TasksAdapter(emptyList())
    private var notesAdapter: NotesAdapter? = null
    private var callBacks: CallBacks? = null
    private lateinit var fabAdd: FloatingActionButton

    private val notesListViewModel: NotesListViewModel by lazy {
        ViewModelProviders.of(this).get(NotesListViewModel::class.java)
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
        initUi()
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

    private fun initUi() {
        rvNotes.layoutManager = LinearLayoutManager(context)
        rvNotes.adapter = notesAdapter
    }

    private fun updateUI(notes: List<NotesModel>) {
        notesAdapter =
            NotesAdapter(notes, tasksAdapter, context,callBacks)
        rvNotes.adapter = notesAdapter

    }

    private fun renderUI() {
        notesListViewModel.notesLiveData.observe(
            viewLifecycleOwner,
            Observer { notes ->
                updateUI(notes)
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