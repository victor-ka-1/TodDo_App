package com.kasko.victor.testapp.toddoapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kasko.victor.testapp.toddoapp.ui.fragments.TasksFragment
import com.kasko.victor.testapp.toddoapp.ui.fragments.UsersFragment

enum class Tab{
    TASKS{
        override fun getTabName(): String = "Tasks List"
    }, USERS{
        override fun getTabName(): String = "Users List"
    };
    abstract fun getTabName():String
}
fun getTabByPosition(position: Int): Tab =
    when(position){
        0 -> Tab.TASKS
        1 -> Tab.USERS
        else -> Tab.TASKS
    }

class FragmentsAdapter (fm: FragmentManager, lifecycle: Lifecycle) :FragmentStateAdapter(fm,lifecycle){
    override fun getItemCount(): Int =
        Tab.values().size

    override fun createFragment(position: Int): Fragment =
        when(getTabByPosition(position)){
            Tab.TASKS -> { TasksFragment.newInstance() }
            Tab.USERS -> { UsersFragment.newInstance() }
        }
}