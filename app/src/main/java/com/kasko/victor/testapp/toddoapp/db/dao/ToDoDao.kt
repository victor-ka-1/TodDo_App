package com.kasko.victor.testapp.toddoapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasko.victor.testapp.toddoapp.api.models.ToDoTaskRemote
import com.kasko.victor.testapp.toddoapp.db.entity.TaskWithUser
import com.kasko.victor.testapp.toddoapp.db.entity.ToDoTaskLocal

@Dao
interface ToDoDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: ToDoTaskLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskList(list : List<ToDoTaskLocal> )

    @Query("DELETE FROM todoTasks")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM todoTasks")
    fun getAllTasks(): LiveData<List<ToDoTaskLocal>>

    @Query("SELECT * FROM todoTasks WHERE userId = :user_id")
     suspend fun getTasksForUser(user_id:Int):List<TaskWithUser>

    @Transaction
    @Query("SELECT * FROM todoTasks")
    fun getAllTasksWithUser() : LiveData<List<TaskWithUser>>

}
