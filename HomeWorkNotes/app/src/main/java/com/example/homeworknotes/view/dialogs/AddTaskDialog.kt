package com.example.homeworknotes.view.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.homeworknotes.R


class AddTasksDialog : DialogFragment() {

    private lateinit var edtTask: EditText
    private lateinit var btnSave: Button
    private var listener: ListenerTask? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.add_task_dialog, container, false)

        edtTask = view.findViewById(R.id.edt_task)
        btnSave = view.findViewById(R.id.btn_save)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiConfig()
        saveTask()
        initTargetFragment()
    }

    private fun uiConfig() {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initTargetFragment() {
        targetFragment?.let { fragment ->
            (fragment as ListenerTask).getTask(edtTask.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    private fun saveTask() {
        btnSave.setOnClickListener {
            initTargetFragment()
            dismiss()
        }

    }

    companion object {
        fun newInstance(): AddTasksDialog {
            return AddTasksDialog()
        }
    }

    interface ListenerTask {
        fun getTask(task: String)
    }
}