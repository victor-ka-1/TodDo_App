package com.kasko.victor.testapp.toddoapp.db.entity

import androidx.room.*
import com.kasko.victor.testapp.toddoapp.api.models.UserRemote
import java.io.Serializable


@Entity(tableName = "todoTasks")
data class ToDoTaskLocal(
    @ColumnInfo(name = "userId")
    val userId: Int,
    @PrimaryKey
    val taskId :Int,
    val title : String,
    val isCompleted: Boolean = false
) :Serializable

