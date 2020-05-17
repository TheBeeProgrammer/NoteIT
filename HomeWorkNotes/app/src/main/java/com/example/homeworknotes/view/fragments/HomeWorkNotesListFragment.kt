package com.example.homeworknotes.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknotes.R
import com.example.homeworknotes.model.HomeWorkNotes
import com.example.homeworknotes.view.adapter.NotesAdapter
import com.example.homeworknotes.viewmodel.HomeWorkNotesListViewModel
import java.util.*


class HomeWorkNotesListFragment : Fragment(), NotesAdapter . Tap {

    private lateinit var rvNotes: RecyclerView
    private var notesAdapter: NotesAdapter? = NotesAdapter(emptyList())
    private var callBacks: CallBacks? = null

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
        val view = inflater.inflate(R.layout.notes_list_fragment, container, false)
        rvNotes = view.findViewById(R.id.rv_listNotes)
        rvNotes.layoutManager = LinearLayoutManager(context)
        rvNotes.adapter = notesAdapter
        return view
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
      /*  homeWorkNotesListViewModel.notesListLiveData.observe(viewLifecycleOwner,
            Observer { notes ->
                notes.let {
                    Log.i("HOMEWORKNOTESLIST", " GOT IT ${notes.size}")
                    updateUI(notes)
                }
            })*/
        updateUI(homeWorkNotesListViewModel.homeWorkNotesList)
    }

    private fun updateUI(notes: List<HomeWorkNotes>) {
        notesAdapter =
            NotesAdapter(notes)
        rvNotes.adapter = notesAdapter

    }

    companion object {
        fun newInstance(): HomeWorkNotesListFragment {
            return HomeWorkNotesListFragment()
        }
    }

    interface CallBacks {
        fun onHomeWorkSelected(id:UUID)
    }

    override fun onItemClick(id: UUID) {
            callBacks?.onHomeWorkSelected(id)
            Log.d("MAIN.CallBAck", "From Fragment$id")
    }

}