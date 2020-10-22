package com.kasko.victor.testapp.toddoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.kasko.victor.testapp.toddoapp.R
import com.kasko.victor.testapp.toddoapp.ui.viewmodels.ToDoViewModel
import com.kasko.victor.testapp.toddoapp.ui.adapters.TaskRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TasksFragment : Fragment() {

    private val viewModel: ToDoViewModel by sharedViewModel()
    private lateinit var rwAdapter: TaskRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        setupObservers()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TasksFragment()
    }

    private fun setupUI(){
        rwAdapter = TaskRecyclerViewAdapter()
        recyclerView_tasks.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = rwAdapter
        }
    }

    private fun setupObservers() {
        viewModel.tasksWithUser.observe(viewLifecycleOwner){ tasksList ->
            rwAdapter.updateRecyclerViewList(tasksList)
        }
    }
}