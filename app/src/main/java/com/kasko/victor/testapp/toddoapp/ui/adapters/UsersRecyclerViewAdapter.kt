package com.kasko.victor.testapp.toddoapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kasko.victor.testapp.toddoapp.R
import com.kasko.victor.testapp.toddoapp.db.entity.ToDoTaskLocal
import com.kasko.victor.testapp.toddoapp.db.entity.UserLocal
import com.kasko.victor.testapp.toddoapp.ui.fragments.UserListListeners
import kotlinx.android.synthetic.main.task_item.view.*
import kotlinx.android.synthetic.main.user_item.view.*


class UsersRecyclerViewAdapter(private val listeners: UserListListeners) : RecyclerView.Adapter<UserViewHolder>(){
    private val userList: ArrayList<UserLocal> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
         UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false),
            listeners = listeners
         )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(userList[position])


    override fun getItemCount(): Int =
        userList.size

    fun updateRecyclerViewList( newList: List<UserLocal>){
        val taskDiffUtilCallback = UserDiffUtil(userList, newList)
        val taskDiffResult = DiffUtil.calculateDiff(taskDiffUtilCallback)
        userList.clear()
        userList.addAll(newList)
        taskDiffResult.dispatchUpdatesTo(this)
    }
}

class UserViewHolder(private val view1: View,
                     private val listeners: UserListListeners) : RecyclerView.ViewHolder(view1){
    fun bind( userLocal: UserLocal){
        view1.apply {
            tv_name_userItem.text = userLocal.name
            setOnClickListener{
                listeners.onItemClick(userLocal)
            }
        }
    }
}

class UserDiffUtil(private val oldList: List<UserLocal>, private val newList: List<UserLocal>) : DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
         oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        (oldList[oldItemPosition] == newList[newItemPosition])
}