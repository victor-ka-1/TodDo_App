package com.kasko.victor.testapp.toddoapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kasko.victor.testapp.toddoapp.R
import com.kasko.victor.testapp.toddoapp.db.entity.TaskWithUser
import kotlinx.android.synthetic.main.task_item.view.*


class TaskRecyclerViewAdapter : RecyclerView.Adapter<TaskViewHolder>(){
    private val taskList: ArrayList<TaskWithUser> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
         TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) =
        holder.bind(taskList[position])

    override fun getItemCount(): Int =
        taskList.size

    fun updateRecyclerViewList(newList: List<TaskWithUser>){
        val taskDiffResult = DiffUtil.calculateDiff(TaskDiffUtil(taskList, newList))
        taskList.clear()
        taskList.addAll(newList)
        taskDiffResult.dispatchUpdatesTo(this)
    }
}

class TaskViewHolder(private val view: View) :RecyclerView.ViewHolder(view){
    fun bind(taskWithUser: TaskWithUser){
        view.apply {
            tv_taskId.text = context.getString(R.string.id_title,taskWithUser.task.taskId)
            tv_title.text = taskWithUser.task.title
            if(taskWithUser.task.isCompleted) {
                tv_status.text = context.getString(R.string.statuse_done_msg)
                cardview_taskItem.setBackgroundColor( ContextCompat.getColor(view.context,R.color.colorDone ))
            }
            else {
                tv_status.text = context.getString(R.string.status_undone_msg)
                cardview_taskItem.setBackgroundColor( ContextCompat.getColor(view.context,R.color.colorInProgress ))
            }
            tv_userName.text = taskWithUser.userLocal?.name
        }
    }
}


class TaskDiffUtil(private val oldList: List<TaskWithUser>,
                   private val newList: List<TaskWithUser>) : DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].task.taskId == newList[newItemPosition].task.taskId

    override fun getOldListSize(): Int =
         oldList.size

    override fun getNewListSize(): Int =
         newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
           (oldList[oldItemPosition].task == newList[newItemPosition].task)
}