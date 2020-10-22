package com.kasko.victor.testapp.toddoapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kasko.victor.testapp.toddoapp.R
import com.kasko.victor.testapp.toddoapp.db.entity.UserLocal
import com.kasko.victor.testapp.toddoapp.ui.viewmodels.ToDoViewModel
import com.kasko.victor.testapp.toddoapp.ui.activities.UserActivity
import com.kasko.victor.testapp.toddoapp.ui.adapters.UsersRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

interface UserListListeners {
    fun onItemClick(user :UserLocal)
}

class UsersFragment : Fragment() {
    private val viewModel: ToDoViewModel by sharedViewModel()
    private lateinit var rvAdapter: UsersRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        setupObservers()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UsersFragment()
    }

    private fun setupUI(){
        rvAdapter = UsersRecyclerViewAdapter(object : UserListListeners{
            override fun onItemClick(user: UserLocal) {
                startActivity(
                    Intent(context,UserActivity::class.java).apply {
                        putExtra(getString(R.string.user_bundle), user)
                    }
                )
            }
        })
        recyclerView_users.apply{
            layoutManager = GridLayoutManager(activity, 3)
            adapter = rvAdapter
        }
    }
    private fun setupObservers() {
        viewModel.allUsersLiveData.observe(viewLifecycleOwner){ userList ->
            rvAdapter.updateRecyclerViewList(userList)
        }
    }
}
